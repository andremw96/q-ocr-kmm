package com.andremw96.qocrkmm.android

import android.app.Application
import com.andremw96.qocrkmm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class QOcrApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@QOcrApp)
            androidLogger()
            modules(
                appModule()
            )
        }
    }
}
