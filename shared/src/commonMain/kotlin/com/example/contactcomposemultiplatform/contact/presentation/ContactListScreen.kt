package com.example.contactcomposemultiplatform.contact.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contactcomposemultiplatform.contact.domain.Contact
import com.example.contactcomposemultiplatform.contact.presentation.components.AddContactSheet
import com.example.contactcomposemultiplatform.contact.presentation.components.ContactDetailSheet
import com.example.contactcomposemultiplatform.contact.presentation.components.ContactListItem
import com.example.contactcomposemultiplatform.contact.presentation.components.RecentlyEditContacts
import com.example.contactcomposemultiplatform.core.presentation.ImagePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    state: ContactListState,
    newContact: Contact?,
    onEvent: (ContactListEvent) -> Unit,
    imagePicker: ImagePicker
) {
    imagePicker.registerPicker { imageBytes ->
        onEvent(
            ContactListEvent.OnPhotoPicked(imageBytes)
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add Contact"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                RecentlyEditContacts(
                    modifier = Modifier,
                    contacts = state.recentlyAddedContacts,
                    onClick = {
                        onEvent(ContactListEvent.SelectContact(it))
                    }
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "My Contacts (${state.contacts.size})",
                    fontWeight = FontWeight.Bold
                )
            }

            items(
                items = state.contacts,
                key = { contact -> contact.id ?: contact.phoneNumber }
            ) { contact ->
                ContactListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(
                                ContactListEvent.SelectContact(contact)
                            )
                        }
                        .padding(horizontal = 16.dp),
                    contact = contact
                )
            }
        }
    }
    
    ContactDetailSheet(
        modifier = Modifier,
        isOpen = state.isSelectedContactSheetOpen,
        selectedContact = state.selectedContact,
        onEvent = onEvent
    )

    AddContactSheet(
        state = state,
        newContact = newContact,
        isOpen = state.isAddContactSheetOpen,
        onEvent = { event ->
            if (event is ContactListEvent.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }

            onEvent(event)
        }
    )
}