package com.andremw96.qocrkmm.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun CameraView(
    modifier: Modifier,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
)
