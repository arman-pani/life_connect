package com.example.lifeconnect

import android.app.Application
import androidx.compose.runtime.*
import com.example.lifeconnect.di.ktorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class LifeConnectApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LifeConnectApplication)
            modules(ktorModule)
        }
    }
}