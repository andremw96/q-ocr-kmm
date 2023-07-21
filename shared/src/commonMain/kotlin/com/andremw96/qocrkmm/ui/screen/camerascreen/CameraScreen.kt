package com.andremw96.qocrkmm.ui.screen.camerascreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.andremw96.qocrkmm.ui.CameraView
import kotlinx.coroutines.delay

@Composable
fun CameraScreenComposable(
    onTextGenerated: (String, ImageBitmap) -> Unit,
    onBack: (Boolean) -> Unit,
) {
    var showCamera by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!showCamera) {
            delay(300) // for animation
            showCamera = true
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Take Photo") },
                navigationIcon = {
                    IconButton(onClick = { onBack(false) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
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
    )
}
