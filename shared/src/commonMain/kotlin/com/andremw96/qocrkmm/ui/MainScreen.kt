package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.android.MyApplicationTheme

@Composable
fun MainScreen(
    modifier: Modifier,
    openCameraClicked: () -> Unit,
    bitmap: ImageBitmap?,
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
                Button(onClick = {
                    openCameraClicked()
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
