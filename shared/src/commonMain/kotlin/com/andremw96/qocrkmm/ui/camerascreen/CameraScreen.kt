package com.andremw96.qocrkmm.ui.camerascreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.andremw96.qocrkmm.ui.CameraView
import kotlinx.coroutines.delay

@Composable
fun CameraScreenComposable(
    onTextGenerated: (String, ImageBitmap) -> Unit,
) {
    var showCamera by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!showCamera) {
            delay(300) // for animation
            showCamera = true
        }
    }
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        if (showCamera) {
            CameraView(Modifier.fillMaxSize(), onTextGenerated = { text, imageBitmap ->
                if (imageBitmap != null) {
                    onTextGenerated(
                        text, imageBitmap
                    )
                }
            })
        }
    }
}
