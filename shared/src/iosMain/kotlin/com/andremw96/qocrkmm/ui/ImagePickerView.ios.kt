package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.interop.LocalUIViewController
import com.andremw96.qocrkmm.readBytes
import com.andremw96.qocrkmm.toImageBitmap
import kotlinx.coroutines.launch
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.darwin.NSObject

@Composable
actual fun ImagePickerView(
    onTextGenerated: (text: String, image: ImageBitmap?) -> Unit
) {
    val capturedPhoto = remember {
        mutableStateOf<ImageBitmap?>(null)
    }
    val coroutineScope = rememberCoroutineScope()
    val uiViewController = LocalUIViewController.current
    val pickerDelegate = remember {
        object : NSObject(), PHPickerViewControllerDelegateProtocol {
            override fun picker(picker: PHPickerViewController, didFinishPicking: List<*>) {
                didFinishPicking.forEach {
                    val result = it as PHPickerResult
                    result.itemProvider.loadFileRepresentationForTypeIdentifier(
                        "public.item",
                        completionHandler = { imgUrl, error ->
                            coroutineScope.launch {
                                capturedPhoto.value = imgUrl?.readBytes()?.toImageBitmap()
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        capturedPhoto.value?.let { image ->
            Image(
                bitmap = image,
                contentDescription = "Your Image",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

