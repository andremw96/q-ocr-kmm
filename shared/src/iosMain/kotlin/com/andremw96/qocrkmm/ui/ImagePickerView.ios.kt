package com.andremw96.qocrkmm.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.interop.LocalUIViewController
import kotlinx.coroutines.launch
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.UIKit.UIImage
import platform.darwin.NSObject

@Composable
actual fun ImagePickerView(
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val uiViewController = LocalUIViewController.current
    val pickerDelegate = remember {
        object : NSObject(), PHPickerViewControllerDelegateProtocol {
            override fun picker(picker: PHPickerViewController, didFinishPicking: List<*>) {
                didFinishPicking.forEach {
                    val result = it as PHPickerResult
                    result.itemProvider.loadDataRepresentationForTypeIdentifier(
                        "public.item",
                        completionHandler = { imgData, error ->
                            coroutineScope.launch {
                                if (imgData != null) {
                                    val uiImage = UIImage(imgData)
                                    generateTextFromImage(uiImage, onTextGenerated)
                                }
                            }
                        }
                    )
                }
                picker.dismissViewControllerAnimated(flag = false, completion = null)
            }
        }
    }

    remember {
        val config = PHPickerConfiguration()
        val pickerController = PHPickerViewController(config)
        pickerController.setDelegate(pickerDelegate)
        uiViewController.presentViewController(
            pickerController,
            animated = true,
            completion = null
        )
    }
}

