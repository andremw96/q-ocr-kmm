package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

@Composable
fun ExtractedTextResultScreen(
    extractedText: String,
    image: ImageBitmap,
) {
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        Column {
            Text(extractedText)

            Image(bitmap = image, null)
        }
    }
}
