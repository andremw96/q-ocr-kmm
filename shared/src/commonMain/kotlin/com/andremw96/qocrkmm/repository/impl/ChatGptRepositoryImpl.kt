package com.andremw96.qocrkmm.repository.impl

import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.data.network.ChatGptService
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.domain.MapCompletionResponseToSchema
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema
import com.andremw96.qocrkmm.repository.ChatGptRepository

class ChatGptRepositoryImpl(
    private val chatGptService: ChatGptService,
    private val mapper: MapCompletionResponseToSchema,
) : ChatGptRepository {
    override suspend fun completions(request: CompletionRequest): Resource<CompletionSchema, CompletionErrorSchema> {
        return mapper.invoke(chatGptService.completions(request))
    }
}
