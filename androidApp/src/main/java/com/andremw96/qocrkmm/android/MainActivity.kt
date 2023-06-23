package com.andremw96.qocrkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andremw96.qocrkmm.domain.GetCompletions
import com.andremw96.qocrkmm.ui.MainScreenAndroid
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val getCompletions : GetCompletions by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenAndroid(
                getCompletions,
            )
        }
    }
}
