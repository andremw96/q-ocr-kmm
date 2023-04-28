package com.andremw96.qocrkmm.di

import com.andremw96.qocrkmm.repository.ChatGptRepository
import com.andremw96.qocrkmm.repository.impl.ChatGptRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatGptRepository> {
        ChatGptRepositoryImpl(get(), get())
    }
}
