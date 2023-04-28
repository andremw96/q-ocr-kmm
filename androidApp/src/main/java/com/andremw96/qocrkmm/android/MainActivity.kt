package com.andremw96.qocrkmm.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andremw96.qocrkmm.MainScreenAndroid
import com.andremw96.qocrkmm.data.network.ChatGptService
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.domain.GetCompletions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val getCompletions: GetCompletions by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val response = getCompletions.invoke(
                CompletionRequest(
                    model = "text-davinci-003",
                    prompt = "Say this is a test",
                    maxTokens = 7,
                    temperature = 0,
                    topP = 1,
                    n = 1,
                    stream = false,
                    logprobs = null,
                    stop = "\n"
                )
            )
            Log.d("response", "$response")
        }

        setContent {
            MyApplicationTheme {
                MainScreenAndroid()
            }
        }
    }
}
