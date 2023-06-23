package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.ui.view.BackButton
import com.andremw96.qocrkmm.ui.view.TopLayout

@Composable
fun ExtractedTextResultScreen(
    extractedText: String,
    image: ImageBitmap,
    onBack: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Extracted Text Result Screen") },
                navigationIcon = {
                    IconButton(onClick = { onBack(false) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = extractedText,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Image(
                    bitmap = image,
                    contentDescription = "My Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Selectable Chips:",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                SelectableChipRow(
                    chipList = listOf("Chip 1", "Chip 2", "Chip 3"),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    )
}

@Composable
fun SelectableChipRow(chipList: List<String>, modifier: Modifier = Modifier) {
    var selectedChips by remember { mutableStateOf(setOf<String>()) }

    Row(modifier = modifier) {
        chipList.forEach { chipText ->
            val isSelected = selectedChips.contains(chipText)
            ToggleButton(
                checked = isSelected,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        selectedChips += chipText
                    } else {
                        selectedChips -= chipText
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = chipText)
            }
        }
    }
}

@Composable
fun ToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
        )

        content()
    }
}
