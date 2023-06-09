package com.andremw96.qocrkmm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.andremw96.qocrkmm.ui.MainScreen
import kotlinx.coroutines.runBlocking
import org.jetbrains.skia.Image
import platform.UIKit.UIImage

fun MainViewController(
    openCameraClicked: () -> Unit,
) = ComposeUIViewController {
//    LaunchedEffect(image) {
//        imageBitmap = getTempImageURL(image)?.readBytes()?.toImageBitmap()
//    }

    MainScreen(
        modifier = Modifier.fillMaxWidth(),
        openCameraClicked = openCameraClicked,
        bitmap = null
    )
}
