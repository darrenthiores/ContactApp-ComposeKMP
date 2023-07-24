package com.example.contactcomposemultiplatform.contact.data

import com.example.contactcomposemultiplatform.contact.domain.Contact
import com.example.contactcomposemultiplatform.contact.domain.ContactDataSource
import com.example.contactcomposemultiplatform.core.data.ImageStorage
import com.example.contactcomposemultiplatform.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase,
    private val imageStorage: ImageStorage
): ContactDataSource {
    private val queries = db.contactQueries

    override fun getContacts(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity ->
                            async {
                                entity.toContact(
                                    imageStorage = imageStorage
                                )
                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(
                amount = amount.toLong()
            )
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity ->
                            async {
                                entity.toContact(
                                    imageStorage = imageStorage
                                )
                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
        val imagePath = contact.photoBytes?.let {
            imageStorage.saveImage(it)
        }

        queries
            .insertContact(
                id = contact.id,
                firstName = contact.firstName,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                createdAt = Clock.System.now().toEpochMilliseconds(),
                imagePath = imagePath
            )
    }

    override suspend fun deleteContact(id: Long) {
        val entity = queries
            .getContactById(id)
            .executeAsOne()

        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }

        queries
            .deleteContact(
                id = id
            )
    }
}