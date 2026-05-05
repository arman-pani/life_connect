package com.example.lifeconnect

import com.example.lifeconnect.data.local.DbBuilderFactory
import org.koin.dsl.module

val platformModule = module {
    single { DbBuilderFactory(get()) }
    single<ImageStorageUtils> { AndroidImageStorageUtils(get()) }
}