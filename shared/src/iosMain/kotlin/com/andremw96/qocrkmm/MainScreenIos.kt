package com.andremw96.qocrkmm

import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.ComposeUIViewController
import com.andremw96.qocrkmm.ui.MainScreen
import org.jetbrains.skia.Image

fun MainViewController(
    openCameraClicked: (Boolean) -> Unit,
    bitmapImageResult: Image?,
) = ComposeUIViewController {
    MainScreen(openCameraClicked, bitmapImageResult?.toComposeImageBitmap())
}
