package com.andremw96.qocrkmm.ui

import androidx.compose.ui.graphics.ImageBitmap
import cocoapods.GoogleMLKit.MLKCommonTextRecognizerOptions
import cocoapods.GoogleMLKit.MLKTextRecognitionCallback
import cocoapods.GoogleMLKit.MLKTextRecognizer
import cocoapods.GoogleMLKit.MLKVisionImage
import com.andremw96.qocrkmm.getTempImageURL
import com.andremw96.qocrkmm.readBytes
import com.andremw96.qocrkmm.toImageBitmap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import objcnames.classes.MLKText
import objcnames.protocols.MLKCompatibleImageProtocol
import platform.Foundation.NSError
import platform.UIKit.UIImage

fun generateTextFromImage(
    image: UIImage,
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit,
) {
    val latinOptions = MLKCommonTextRecognizerOptions()
    val latinTextRecognizer = MLKTextRecognizer.textRecognizerWithOptions(options = latinOptions)

    val visionImage = MLKVisionImage(image = image)
    val imageTempUrl = getTempImageURL(image)

    latinTextRecognizer.processImage(image = visionImage as MLKCompatibleImageProtocol, object : MLKTextRecognitionCallback {
        override fun invoke(result: MLKText?, error: NSError?) {
            GlobalScope.launch {
                onTextGenerated(result.toString(), imageTempUrl?.readBytes()?.toImageBitmap())
            }
        }
    })
}
