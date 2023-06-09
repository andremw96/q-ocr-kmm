package com.andremw96.qocrkmm.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.andremw96.qocrkmm.ui.icon.IconPhotoCamera
import com.andremw96.qocrkmm.ui.view.CircularButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.absoluteValue

private val executor = Executors.newSingleThreadExecutor()

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalGetImage
@Composable
actual fun CameraView(
    modifier: Modifier,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    val cameraPermissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.CAMERA,
        )
    )
    if (cameraPermissionState.allPermissionsGranted) {
        CameraWithGrantedPermission(modifier, onTextGenerated)
    } else {
        LaunchedEffect(Unit) {
            cameraPermissionState.launchMultiplePermissionRequest()
        }
    }
}

@SuppressLint("MissingPermission")
@ExperimentalGetImage
@Composable
private fun CameraWithGrantedPermission(
    modifier: Modifier,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    var isFrontCamera by rememberSaveable { mutableStateOf(false) }
    val cameraSelector = remember(isFrontCamera) {
        val lensFacing =
            if (isFrontCamera) {
                CameraSelector.LENS_FACING_FRONT
            } else {
                CameraSelector.LENS_FACING_BACK
            }
        CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()
    }

    LaunchedEffect(isFrontCamera) {
        val cameraProvider = suspendCoroutine<ProcessCameraProvider> { continuation ->
            ProcessCameraProvider.getInstance(context).also { cameraProvider ->
                cameraProvider.addListener({
                    continuation.resume(cameraProvider.get())
                }, executor)
            }
        }
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    var capturePhotoStarted by remember { mutableStateOf(false) }

    Box(modifier = modifier.pointerInput(isFrontCamera) {
        detectHorizontalDragGestures { _, dragAmount ->
            if (dragAmount.absoluteValue > 50.0) {
                isFrontCamera = !isFrontCamera
            }
        }
    }) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
        CircularButton(
            imageVector = IconPhotoCamera,
            modifier = Modifier.align(Alignment.BottomCenter).padding(36.dp),
            enabled = !capturePhotoStarted,
        ) {
            capturePhotoStarted = true
            imageCapture.takePicture(executor, object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(imageProxy: ImageProxy) {
                    imageProxy.image?.let {
                        generateTextFromImage(
                            imageProxy = imageProxy,
                            rotationDegrees = imageProxy.imageInfo.rotationDegrees,
                            onTextGenerated = onTextGenerated,
                        )
                        capturePhotoStarted = false
                    }
                    imageProxy.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("cameraview", exception.localizedMessage)
                }
            })
        }
        if (capturePhotoStarted) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp).align(Alignment.Center),
                color = Color.Cyan.copy(alpha = 0.7f),
                strokeWidth = 8.dp,
            )
        }
    }
}
