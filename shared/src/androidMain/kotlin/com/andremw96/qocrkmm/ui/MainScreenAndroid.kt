package com.andremw96.qocrkmm.ui

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun MainScreenAndroid(
    bitmapImageResult: Bitmap?,
    openCameraClicked: () -> Unit,
) = MainScreen(
    modifier = Modifier.fillMaxSize(),
    openCameraClicked = openCameraClicked, bitmap = bitmapImageResult?.asImageBitmap()
)
