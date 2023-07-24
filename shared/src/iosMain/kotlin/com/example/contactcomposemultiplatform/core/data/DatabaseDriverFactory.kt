package com.example.contactcomposemultiplatform.core.data

import com.example.contactcomposemultiplatform.database.ContactDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = ContactDatabase.Schema,
            name = "contact.db"
        )
    }
}