package com.andremw96.qocrkmm.di

import com.andremw96.qocrkmm.domain.GetCompletions
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}

class InjectionHelper : KoinComponent {
    val getCompletions: GetCompletions by inject()
}
