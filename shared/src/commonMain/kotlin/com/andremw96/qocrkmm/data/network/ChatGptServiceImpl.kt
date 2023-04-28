package com.andremw96.qocrkmm.data.network

import com.andremw96.qocrkmm.BuildKonfig
import com.andremw96.qocrkmm.common.ApiResponse
import com.andremw96.qocrkmm.data.network.model.CompletionErrorResponse
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.data.network.model.CompletionResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ChatGptServiceImpl() : ChatGptService {
    private val API_TOKEN = BuildKonfig.CHAT_GPT_API_KEY
    private val API_URL = BuildKonfig.BASE_API_URL

    private val client = HttpClient() {
        defaultRequest {
            header("Authorization", "Bearer $API_TOKEN")
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    override suspend fun completions(request: CompletionRequest): ApiResponse<CompletionResponse, CompletionErrorResponse> {
        val url = "$API_URL/completions"
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        return if (response.status == HttpStatusCode.OK) {
            ApiResponse.Success(response.body())
        } else {
            ApiResponse.Error(response.body())
        }
    }
}
