package com.andremw96.qocrkmm.data.network

import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import io.ktor.client.statement.*

interface ChatGptService {
    suspend fun completions(request: CompletionRequest): HttpResponse
}
