package com.example.lifeconnect

import com.example.lifeconnect.data.local.DbBuilderFactory
import org.koin.dsl.module
import platform.UIKit.UIDevice

val platformModule = module {
    single { DbBuilderFactory() }
}