package com.andremw96.qocrkmm.data.network

import com.andremw96.qocrkmm.data.network.model.ApiResponse
import com.andremw96.qocrkmm.data.network.model.CompletionErrorResponse
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import io.ktor.client.statement.*

interface ChatGptService {
    suspend fun completions(request: CompletionRequest): ApiResponse<CompletionResponse, CompletionErrorResponse>
}
