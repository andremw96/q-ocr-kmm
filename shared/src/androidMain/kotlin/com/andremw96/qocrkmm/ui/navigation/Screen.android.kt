package com.andremw96.qocrkmm.ui.navigation

import android.os.Parcelable
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.parcelize.Parcelize

@Parcelize
actual class CameraScreen : Screen, Parcelable
@Parcelize
actual class ImagePickerScreen : Screen, Parcelable
@Parcelize
actual class MenuScreen : Screen, Parcelable
actual class ExtractedTextScreen actual constructor(
    actual val extractedText: String,
    actual val capturedImage: ImageBitmap,
) : Screen
