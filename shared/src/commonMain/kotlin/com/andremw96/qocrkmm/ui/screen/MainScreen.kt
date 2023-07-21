package com.andremw96.qocrkmm.ui.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andremw96.qocrkmm.android.MyApplicationTheme
import com.andremw96.qocrkmm.domain.GetCompletions
import com.andremw96.qocrkmm.ui.navigation.*
import com.andremw96.qocrkmm.ui.screen.camerascreen.CameraScreenComposable
import com.andremw96.qocrkmm.ui.screen.extractedtextscreen.ExtractedTextResultScreen
import com.andremw96.qocrkmm.ui.screen.imagepickerscreen.ImagePickerScreenComposable
import com.andremw96.qocrkmm.ui.screen.menuscreen.MenuScreenComposable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    getCompletions: GetCompletions,
) {
    MyApplicationTheme {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            val navigationStack = rememberSaveable(
                saver = listSaver<NavigationStack<Screen>, Screen>(
                    restore = { NavigationStack(*it.toTypedArray()) },
                    save = { it.stack },
                )
            ) {
                NavigationStack(MenuScreen())
            }

            AnimatedContent(targetState = navigationStack.lastWithIndex(), transitionSpec = {
                val previousIdx = initialState.index
                val currentIdx = targetState.index
                val multiplier = if (previousIdx < currentIdx) 1 else -1
                if (initialState.value is CameraScreen && targetState.value is ExtractedTextScreen) {
                    fadeIn() with fadeOut(tween(durationMillis = 500, 500))
                } else {
                    slideInHorizontally { w -> multiplier * w } with
                            slideOutHorizontally { w -> multiplier * -1 * w }
                }
            }) { (_, page) ->
                when (page) {
                    is ImagePickerScreen -> {
                        ImagePickerScreenComposable(
                            onTextGenerated = { extractedText, capturedImage ->
                                navigationStack.push(
                                    ExtractedTextScreen(
                                        extractedText, capturedImage
                                    )
                                )
                            },
                            onBack = {
                                navigationStack.back()
                            }
                        )
                    }
                    is CameraScreen -> {
                        CameraScreenComposable(
                            onTextGenerated = { extractedText, capturedImage ->
                                navigationStack.push(
                                    ExtractedTextScreen(
                                        extractedText, capturedImage
                                    )
                                )
                            },
                            onBack = {
                                navigationStack.back()
                            }
                        )
                    }
                    is ExtractedTextScreen -> {
                        ExtractedTextResultScreen(
                            extractedText = page.extractedText,
                            image = page.capturedImage,
                            onBack = {
                                navigationStack.back()
                            },
                            getCompletions = getCompletions,
                        )
                    }
                    is MenuScreen -> {
                        MenuScreenComposable(
                            navigationStack
                        )
                    }
                }
            }
        }
    }
}
