package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.android.MyApplicationTheme

@Composable
fun MainScreen(
    modifier: Modifier,
) {
    MyApplicationTheme {
        Scaffold(
            modifier = modifier
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CameraScreen()

//                if (bitmap != null) {
//                    Image(bitmap = bitmap, "")
//                }
            }
        }
    }
}
