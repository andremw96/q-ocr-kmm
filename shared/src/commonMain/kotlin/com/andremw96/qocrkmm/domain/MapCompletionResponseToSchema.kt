package com.andremw96.qocrkmm.domain

import com.andremw96.qocrkmm.common.ApiResponse
import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.data.network.model.CompletionErrorResponse
import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema

interface MapCompletionResponseToSchema {
    fun invoke(apiResponse: ApiResponse<CompletionResponse, CompletionErrorResponse>): Resource<CompletionSchema, CompletionErrorSchema>
}
