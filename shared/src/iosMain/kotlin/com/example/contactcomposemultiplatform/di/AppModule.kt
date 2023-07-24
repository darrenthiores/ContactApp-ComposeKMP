package com.example.contactcomposemultiplatform.di

import com.example.contactcomposemultiplatform.contact.data.SqlDelightContactDataSource
import com.example.contactcomposemultiplatform.contact.domain.ContactDataSource
import com.example.contactcomposemultiplatform.core.data.DatabaseDriverFactory
import com.example.contactcomposemultiplatform.core.data.ImageStorage
import com.example.contactcomposemultiplatform.database.ContactDatabase

actual class AppModule {
    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
}