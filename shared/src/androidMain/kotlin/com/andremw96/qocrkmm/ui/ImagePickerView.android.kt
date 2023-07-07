package com.andremw96.qocrkmm.ui

import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
@androidx.camera.core.ExperimentalGetImage
actual fun ImagePickerView(
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val generateTextStarted = remember { mutableStateOf(false) }
        val context = LocalContext.current
        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                if (uri != null) {
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(
                                context.contentResolver,
                                uri
                            )
                        )
                    } else {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    }

                    generateTextStarted.value = true

                    generateTextFromImage(
                        imageBitmap = bitmap,
                        rotationDegrees = 0,
                        onTextGenerated = { generatedText, imageBitmap ->
                            onTextGenerated(generatedText, imageBitmap)
                        },
                        generateTextStarted = generateTextStarted,
                    )
                }
            }
        )

        LaunchedEffect(Unit) {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

        if (generateTextStarted.value) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp).align(Alignment.Center),
                color = Color.Cyan.copy(alpha = 0.7f),
                strokeWidth = 8.dp,
            )
        }
    }
}

