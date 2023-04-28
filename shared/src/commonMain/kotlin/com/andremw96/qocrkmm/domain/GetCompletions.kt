package com.andremw96.qocrkmm.domain

import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema

interface GetCompletions {
    suspend fun invoke(request: CompletionRequest): Resource<CompletionSchema, CompletionErrorSchema>
}
