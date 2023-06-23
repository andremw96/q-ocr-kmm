package com.andremw96.qocrkmm.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andremw96.qocrkmm.domain.GetCompletions

@Composable
fun MainScreenAndroid(
    getCompletions: GetCompletions,
) = MainScreen(
    modifier = Modifier.fillMaxSize(),
    getCompletions = getCompletions,
)
