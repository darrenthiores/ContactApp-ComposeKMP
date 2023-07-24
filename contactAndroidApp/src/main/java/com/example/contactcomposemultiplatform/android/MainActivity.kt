package com.example.contactcomposemultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import com.example.contactcomposemultiplatform.App
import com.example.contactcomposemultiplatform.core.presentation.ImagePickerFactory
import com.example.contactcomposemultiplatform.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                appModule = AppModule(
                    context = LocalContext.current.applicationContext
                ),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}
