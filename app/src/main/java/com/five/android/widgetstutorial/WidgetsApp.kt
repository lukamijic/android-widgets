package com.five.android.widgetstutorial

import android.app.Application
import com.five.android.widgetstutorial.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WidgetsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WidgetsApp)
            modules(appModule)
        }
    }
}