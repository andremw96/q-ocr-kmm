package com.andremw96.qocrkmm.android

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.andremw96.qocrkmm.ui.MainScreenAndroid

@ExperimentalGetImage
class MainActivity : ComponentActivity() {

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private var bitmapImageResult: MutableState<Bitmap?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenAndroid(bitmapImageResult.value) { openCameraClicked ->
                shouldShowCamera.value = openCameraClicked
            }

            if (shouldShowCamera.value) {
                CameraView(
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }
        }

        requestCameraPermission()
    }

    private fun handleImageCapture(bitmap: Bitmap) {
        shouldShowCamera.value = false
        bitmapImageResult.value = bitmap
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {

        } else {

        }
    }
}
