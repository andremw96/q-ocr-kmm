package com.andremw96.qocrkmm.di

import com.andremw96.qocrkmm.domain.GetCompletions
import com.andremw96.qocrkmm.domain.MapCompletionResponseToSchema
import com.andremw96.qocrkmm.domain.impl.GetCompletionsImpl
import com.andremw96.qocrkmm.domain.impl.MapCompletionResponseToSchemaImpl
import org.koin.dsl.module

val domainModule = module {
    single<MapCompletionResponseToSchema> {
        MapCompletionResponseToSchemaImpl()
    }
    single<GetCompletions> {
        GetCompletionsImpl(get())
    }
}
