package com.example.contactcomposemultiplatform.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.contactcomposemultiplatform.ui.theme.DarkColorScheme
import com.example.contactcomposemultiplatform.ui.theme.LightColorScheme
import com.example.contactcomposemultiplatform.ui.theme.Typography

@Composable
actual fun ContactTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}