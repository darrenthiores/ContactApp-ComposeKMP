package com.example.contactcomposemultiplatform.core.data

import android.content.Context
import com.example.contactcomposemultiplatform.database.ContactDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = ContactDatabase.Schema,
            context = context,
            name = "contact.db"
        )
    }
}