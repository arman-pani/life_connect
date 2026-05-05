@file:JvmName("KtorModuleAndroid")
package com.example.lifeconnect.di


import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

actual fun httpClientEngine(): HttpClientEngineFactory<*> = Android