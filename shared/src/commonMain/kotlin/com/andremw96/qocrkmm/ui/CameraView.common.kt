package com.andremw96.qocrkmm.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CameraView(
    modifier: Modifier,
    onTextGenerated: (text: String) -> Unit
)
