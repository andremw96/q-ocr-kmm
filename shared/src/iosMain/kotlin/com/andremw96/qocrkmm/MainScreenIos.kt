package com.andremw96.qocrkmm

import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.ComposeUIViewController
import com.andremw96.qocrkmm.ui.MainScreen
import org.jetbrains.skia.Image

fun MainViewController(
    openCameraClicked: () -> Unit,
) = ComposeUIViewController {
    MainScreen(openCameraClicked, null)
}
