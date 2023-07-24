package com.example.contactcomposemultiplatform.contact.data

import com.example.contactcomposemultiplatform.contact.domain.Contact
import com.example.contactcomposemultiplatform.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(
    imageStorage: ImageStorage
): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}