package com.andremw96.qocrkmm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.andremw96.qocrkmm.di.InjectionHelper
import com.andremw96.qocrkmm.ui.MainScreen

fun MainViewController(
    injectionHelper: InjectionHelper,
) = ComposeUIViewController {
    MainScreen(
        modifier = Modifier.fillMaxWidth(),
        getCompletions = injectionHelper.getCompletions
    )
}
