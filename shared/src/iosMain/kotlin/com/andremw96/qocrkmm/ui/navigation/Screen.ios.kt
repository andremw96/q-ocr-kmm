package com.andremw96.qocrkmm.ui.navigation

import androidx.compose.ui.graphics.ImageBitmap

actual class CameraScreen : Screen
actual class ImagePickerScreen : Screen
actual class MenuScreen : Screen
actual class ExtractedTextScreen actual constructor(
    actual val extractedText: String,
    actual val capturedImage: ImageBitmap
) : Screen
