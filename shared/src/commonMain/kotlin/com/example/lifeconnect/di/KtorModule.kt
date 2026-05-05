package com.example.lifeconnect.di

import com.example.lifeconnect.data.LifeConnectApi
import com.example.lifeconnect.data.LifeConnectApiImpl
import com.example.lifeconnect.data.local.AppDatabase
import com.example.lifeconnect.data.local.DbBuilderFactory
import com.example.lifeconnect.data.local.getDatabase
import com.example.lifeconnect.data.repository.UserRepository
import com.example.lifeconnect.ui.auth.UserViewModel
import com.example.lifeconnect.viewModels.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// KMP way to define the platform-specific HTTP engine
expect fun httpClientEngine(): HttpClientEngineFactory<*>

val networkModule = module {
    // 1. Singleton for the configured HttpClient
    single {
        HttpClient(httpClientEngine()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            // Add other Ktor configurations like Logging, Timeout, etc.
            defaultRequest {
                header("Content-Type", "application/json")
            }
        }
    }

    // 2. Singleton for the API implementation, injecting the HttpClient
    single<LifeConnectApi> {
        LifeConnectApiImpl(httpClient = get())
    }
}

val databaseModule = module {
    // Inject the AppDatabase using the platform-specific builder
    // We expect the DbBuilderFactory to be provided by the platform
    single { getDatabase(get<DbBuilderFactory>().create()) }

    // Provide the DAO directly for easier injection
    single { get<AppDatabase>().userDao() }
}

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::UserViewModel)
}

val repositoryModule = module {
    singleOf(::UserRepository)
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(networkModule, databaseModule, viewModelModule, repositoryModule)
}