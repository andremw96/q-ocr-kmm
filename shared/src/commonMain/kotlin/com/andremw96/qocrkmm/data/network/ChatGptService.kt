package com.andremw96.qocrkmm.data.network

import com.andremw96.qocrkmm.common.ApiResponse
import com.andremw96.qocrkmm.data.network.model.CompletionErrorResponse
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.data.network.model.CompletionResponse

interface ChatGptService {
    suspend fun completions(request: CompletionRequest): ApiResponse<CompletionResponse, CompletionErrorResponse>
}
