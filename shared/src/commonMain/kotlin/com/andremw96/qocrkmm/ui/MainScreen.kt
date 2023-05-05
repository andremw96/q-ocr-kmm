package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.android.MyApplicationTheme

@Composable
fun MainScreen(
    openCameraClicked: (Boolean) -> Unit,
    bitmap: ImageBitmap?,
) {
    MyApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    openCameraClicked(true)
                }) {
                    Text("Open Camera!")
                }

                if (bitmap != null) {
                    Image(bitmap = bitmap, "")
                }
            }
        }
    }
}
