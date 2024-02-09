package com.emerson.sample.news

import android.app.Application
import com.emerson.sample.news.data.di.DataModule
import com.emerson.sample.news.presentation.di.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                DataModule.module,
                UiModule.module
            )
        }
    }
}
