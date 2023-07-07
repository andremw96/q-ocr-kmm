package com.andremw96.qocrkmm.ui.screen.menuscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.ui.navigation.CameraScreen
import com.andremw96.qocrkmm.ui.navigation.ImagePickerScreen
import com.andremw96.qocrkmm.ui.navigation.NavigationStack
import com.andremw96.qocrkmm.ui.navigation.Screen

@Composable
fun MenuScreenComposable(
    navigationStack: NavigationStack<Screen>,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Choose an Option")

        Button(
            onClick = {
                navigationStack.push(
                    CameraScreen()
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Open Camera")
        }
        Button(
            onClick = {
                navigationStack.push(
                    ImagePickerScreen()
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Open Image Picker")
        }
    }
}
