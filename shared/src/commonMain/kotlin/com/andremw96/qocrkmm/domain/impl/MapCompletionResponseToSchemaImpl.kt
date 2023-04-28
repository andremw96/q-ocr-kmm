package com.andremw96.qocrkmm.domain.impl

import com.andremw96.qocrkmm.common.ApiResponse
import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.data.network.model.CompletionErrorResponse
import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import com.andremw96.qocrkmm.domain.MapCompletionResponseToSchema
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema

class MapCompletionResponseToSchemaImpl : MapCompletionResponseToSchema {
    override fun invoke(apiResponse: ApiResponse<CompletionResponse, CompletionErrorResponse>): Resource<CompletionSchema, CompletionErrorSchema> {
        return when (apiResponse) {
            is ApiResponse.Error -> Resource.Error<CompletionSchema, CompletionErrorSchema>(
                CompletionErrorSchema(
                    code = apiResponse.error.error.code,
                    message = apiResponse.error.error.message,
                    param = apiResponse.error.error.param,
                    type = apiResponse.error.error.type,
                )
            )
            is ApiResponse.Success -> Resource.Success<CompletionSchema, CompletionErrorSchema>(
                CompletionSchema(
                    choices = apiResponse.data.choices.map {
                        CompletionSchema.Choice(
                            finishReason = it.finishReason,
                            index = it.index,
                            logprobs = it.logprobs,
                            text = it.text
                        )
                    },
                    created = apiResponse.data.created,
                    id = apiResponse.data.id,
                    model = apiResponse.data.model,
                    objectX = apiResponse.data.objectX,
                    completionTokens = apiResponse.data.usage.completionTokens,
                    promptTokens = apiResponse.data.usage.promptTokens,
                    totalTokens = apiResponse.data.usage.totalTokens,
                )
            )
        }
    }
}
