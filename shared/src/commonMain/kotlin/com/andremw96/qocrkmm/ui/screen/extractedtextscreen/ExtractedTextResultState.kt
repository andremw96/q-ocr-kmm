package com.andremw96.qocrkmm.ui.screen.extractedtextscreen

import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema

data class ExtractedTextResultState(
    val result: String? = null,
    val error: String? = null,
) {
    companion object {
        fun default(): ExtractedTextResultState = ExtractedTextResultState(
            result = null,
            error = null,
        )
    }
}

fun Resource<CompletionSchema, CompletionErrorSchema>.toState(): ExtractedTextResultState {
    return when (this) {
        is Resource.Error -> ExtractedTextResultState(
            result = null,
            error = this.error.message
        )
        is Resource.Success -> ExtractedTextResultState(
            result = this.data.choices[0].text,
            error = null,
        )
    }
}
