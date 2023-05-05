package com.andremw96.qocrkmm.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun MainScreenAndroid(
    bitmapImageResult: Bitmap?,
    openCameraClicked: (Boolean) -> Unit,
) = MainScreen(openCameraClicked, bitmap = bitmapImageResult?.asImageBitmap())
