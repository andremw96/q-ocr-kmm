package com.andremw96.qocrkmm.domain

import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import com.andremw96.qocrkmm.model.CompletionSchema

interface MapCompletionResponseToSchema {
    fun invoke(apiResponse: CompletionResponse): CompletionSchema
}
