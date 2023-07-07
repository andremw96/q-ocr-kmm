package com.andremw96.qocrkmm.ui.screen.imagepickerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.andremw96.qocrkmm.ui.ImagePickerView
import kotlinx.coroutines.delay

@Composable
fun ImagePickerScreenComposable(
    onTextGenerated: (String, ImageBitmap) -> Unit,
) {
    var showImagePicker by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!showImagePicker) {
            delay(300) // for animation
            showImagePicker = true
        }
    }
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        if (showImagePicker) {
            ImagePickerView(onTextGenerated = { text, imageBitmap ->
                if (imageBitmap != null) {
                    onTextGenerated(
                        text, imageBitmap
                    )
                }
            })
        }
    }
}
