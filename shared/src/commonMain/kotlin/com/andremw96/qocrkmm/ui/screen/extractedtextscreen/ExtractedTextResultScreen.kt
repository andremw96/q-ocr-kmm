package com.andremw96.qocrkmm.ui.screen.extractedtextscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.saveable.rememberSaveable
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
    val isGeneratingResult = remember { mutableStateOf(false) }
    val viewState = remember { mutableStateOf(ExtractedTextResultState.default()) }
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

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
            Box {
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

                if (isGeneratingResult.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(80.dp).align(Alignment.Center),
                        color = Color.Cyan.copy(alpha = 0.7f),
                        strokeWidth = 8.dp,
                    )
                }

                when {
                    viewState.value.result != null -> {
                        Text(
                            text = viewState.value.result!!,
                            modifier = Modifier.background(Color.LightGray)
                                .padding(16.dp)
                                .fillMaxSize()
                        )
                    }

                    viewState.value.error != null -> {
                        Text(
                            text = viewState.value.error!!,
                            modifier = Modifier.background(Color.LightGray)
                                .padding(16.dp)
                                .fillMaxSize()
                        )
                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
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
                                isGeneratingResult.value = true
                                bottomBarState.value = false

                                coroutineScope.launch {
                                    val promptText = extractedText.replace("\n", " ")
                                    val result = getCompletions.invoke(
                                        CompletionRequest(
                                            prompt = ("${selectedChip.value} this text: $promptText"),
                                        )
                                    )

                                    viewState.value = result.toState()

                                    isGeneratingResult.value = false
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
