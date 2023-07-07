package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import platform.AVFoundation.*
import platform.UIKit.*

@Composable
actual fun ImagePickerView(
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text("Image Picker ios")
    }
}

