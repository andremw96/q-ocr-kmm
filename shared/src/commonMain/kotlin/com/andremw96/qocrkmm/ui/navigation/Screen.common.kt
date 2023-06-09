package com.andremw96.qocrkmm.ui.navigation

import androidx.compose.ui.graphics.ImageBitmap

interface Screen

expect class CameraScreen() : Screen

expect class ExtractedTextScreen(
    extractedText: String,
    capturedImage: ImageBitmap
) : Screen {
    val extractedText: String
    val capturedImage: ImageBitmap
}
