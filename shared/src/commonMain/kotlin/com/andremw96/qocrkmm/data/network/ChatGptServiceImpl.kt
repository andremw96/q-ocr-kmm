package com.andremw96.qocrkmm.data.network

import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ChatGptServiceImpl() : ChatGptService {
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
    private val API_TOKEN = "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"
    private val API_URL = "https://api.openai.com/v1/"

    override suspend fun completions(request: CompletionRequest): HttpResponse {
        val url = "$API_URL/completions"
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        return response
    }
}
