package com.andremw96.qocrkmm.repository

import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.model.CompletionSchema

interface ChatGptRepository {
    suspend fun completions(request: CompletionRequest): CompletionSchema
}
