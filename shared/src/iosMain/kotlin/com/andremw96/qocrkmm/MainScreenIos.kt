package com.andremw96.qocrkmm

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.ComposeUIViewController
import com.andremw96.qocrkmm.ui.MainScreen
import kotlinx.coroutines.runBlocking
import org.jetbrains.skia.Image
import platform.UIKit.UIImage

fun MainViewController(
    openCameraClicked: () -> Unit,
    image: UIImage,
) = ComposeUIViewController {
    var imageBitmap = remember<ImageBitmap?> { null }
    println("$image")

    runBlocking {
        imageBitmap = getTempImageURL(image)?.readBytes()?.toImageBitmap()
    }

    MainScreen(openCameraClicked, imageBitmap)
}

fun ByteArray.toImageBitmap(): ImageBitmap =
    Image.makeFromEncoded(this).toComposeImageBitmap()
