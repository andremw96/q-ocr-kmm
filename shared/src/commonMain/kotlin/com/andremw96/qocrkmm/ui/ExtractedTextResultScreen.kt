package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.domain.GetCompletions
import kotlinx.coroutines.launch

enum class ChipActionItem(val string: String) {
    SUMMARY("summary"),
    OUTLINE("outline"),
}

@Composable
fun ExtractedTextResultScreen(
    extractedText: String,
    image: ImageBitmap,
    onBack: (Boolean) -> Unit,
    getCompletions: GetCompletions,
) {
    val selectedChip = remember { mutableStateOf(ChipActionItem.SUMMARY) }
    val coroutineScope = rememberCoroutineScope()

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
            Column {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    item {
                        SelectableChipRow(
                            chipList = ChipActionItem.values().toList(),
                            selectedChip = selectedChip,
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                getCompletions.invoke(
                                    CompletionRequest(
                                        prompt = extractedText.replace("\n", " "),
                                    )
                                )
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(text = "Generate")
                    }
                }
            }
        }
    )
}

@Composable
fun SelectableChipRow(
    chipList: List<ChipActionItem>,
    modifier: Modifier = Modifier,
    selectedChip: MutableState<ChipActionItem>,
) {
    Row(modifier = modifier) {
        chipList.forEach { chipText ->
            val isSelected = selectedChip.value == chipText
            ToggleButton(
                checked = isSelected,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        selectedChip.value = chipText
                    }
                },
            ) {
                Text(text = chipText.string.uppercase())
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
        )

        content()
    }
}
