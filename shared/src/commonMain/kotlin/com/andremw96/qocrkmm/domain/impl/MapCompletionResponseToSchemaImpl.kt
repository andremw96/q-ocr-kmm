package com.andremw96.qocrkmm.domain.impl

import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import com.andremw96.qocrkmm.domain.MapCompletionResponseToSchema
import com.andremw96.qocrkmm.model.CompletionSchema

class MapCompletionResponseToSchemaImpl : MapCompletionResponseToSchema {
    override fun invoke(apiResponse: CompletionResponse): CompletionSchema {
        return CompletionSchema(
            choices = apiResponse.choices.map {
                CompletionSchema.Choice(
                    finishReason = it.finishReason,
                    index = it.index,
                    logprobs = it.logprobs,
                    text = it.text
                )
            },
            created = apiResponse.created,
            id = apiResponse.id,
            model = apiResponse.model,
            objectX = apiResponse.objectX,
            completionTokens = apiResponse.usage.completionTokens,
            promptTokens = apiResponse.usage.promptTokens,
            totalTokens = apiResponse.usage.totalTokens,
        )
    }
}
