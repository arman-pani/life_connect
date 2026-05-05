package com.example.lifeconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifeconnect.di.initKoin
import com.example.lifeconnect.ui.MainScreen
import com.example.lifeconnect.ui.navigation.RootNavHost
import com.example.lifeconnect.ui.screens.home.HomeScreen
import com.example.lifeconnect.ui.screens.requests.screens.LocationPickerScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RootNavHost()
        }
    }
}
