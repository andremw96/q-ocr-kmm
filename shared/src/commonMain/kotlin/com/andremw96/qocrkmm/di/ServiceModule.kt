package com.andremw96.qocrkmm.di

import com.andremw96.qocrkmm.data.network.ChatGptService
import com.andremw96.qocrkmm.data.network.ChatGptServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<ChatGptService> {
        ChatGptServiceImpl()
    }
}
