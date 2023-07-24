package com.example.contactcomposemultiplatform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.contactcomposemultiplatform.contact.presentation.ContactListScreen
import com.example.contactcomposemultiplatform.contact.presentation.ContactListViewModel
import com.example.contactcomposemultiplatform.core.presentation.ContactTheme
import com.example.contactcomposemultiplatform.core.presentation.ImagePicker
import com.example.contactcomposemultiplatform.di.AppModule
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule,
    imagePicker: ImagePicker
) {
    ContactTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        val viewModel = getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory {
                ContactListViewModel(
                    contactDataSource = appModule.contactDataSource
                )
            }
        )
        val state by viewModel.state.collectAsState()
        val newContact = viewModel.newContact

        ContactListScreen(
            state = state,
            newContact = newContact,
            onEvent = { event ->
                viewModel.onEvent(event)
            },
            imagePicker = imagePicker
        )
    }
}