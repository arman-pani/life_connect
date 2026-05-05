package com.example.lifeconnect

import android.app.Application
import com.example.lifeconnect.di.databaseModule
import com.example.lifeconnect.di.networkModule
import com.example.lifeconnect.di.repositoryModule
import com.example.lifeconnect.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LifeConnectApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin events to the Android logger
            androidLogger()
            // Provide the Android context to Koin
            androidContext(this@LifeConnectApplication)
            // Declare your Koin modules
            modules(networkModule, databaseModule, viewModelModule, repositoryModule, platformModule)
        }
//        initKoin {
//            androidContext(this@LifeConnectApplication)
//            androidLogger()
//        }
    }
}
