package com.example.contactcomposemultiplatform.di

import com.example.contactcomposemultiplatform.contact.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}