package com.example.contactcomposemultiplatform.contact.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.contactcomposemultiplatform.contact.domain.Contact
import com.example.contactcomposemultiplatform.contact.presentation.ContactListEvent
import com.example.contactcomposemultiplatform.contact.presentation.ContactListState
import com.example.contactcomposemultiplatform.core.presentation.CustomBottomSheet

@Composable
fun AddContactSheet(
    modifier: Modifier = Modifier,
    state: ContactListState,
    newContact: Contact?,
    isOpen: Boolean,
    onEvent: (ContactListEvent) -> Unit
) {
    CustomBottomSheet(
        modifier = modifier
            .fillMaxSize(),
        visible = isOpen
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                if (newContact?.photoBytes == null) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .clickable {
                                onEvent(ContactListEvent.OnAddPhotoClicked)
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                shape = RoundedCornerShape(40)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp),
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                } else {
                    ContactPhoto(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(ContactListEvent.OnAddPhotoClicked)
                            },
                        contact = newContact
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                ContactTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = newContact?.firstName ?: "",
                    placeholder = "First Name",
                    error = state.firstNameError,
                    onValueChange = {
                        onEvent(
                            ContactListEvent.OnFirstNameChange(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = newContact?.lastName ?: "",
                    placeholder = "Last Name",
                    error = state.lastNameError,
                    onValueChange = {
                        onEvent(
                            ContactListEvent.OnLastNameChange(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = newContact?.email ?: "",
                    placeholder = "Email",
                    error = state.emailError,
                    onValueChange = {
                        onEvent(
                            ContactListEvent.OnEmailChange(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = newContact?.phoneNumber ?: "",
                    placeholder = "Phone Number",
                    error = state.phoneNumberError,
                    onValueChange = {
                        onEvent(
                            ContactListEvent.OnPhoneNumberChange(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onEvent(
                            ContactListEvent.SaveContact
                        )
                    }
                ) {
                    Text(text = "Save")
                }
            }

            IconButton(
                onClick = {
                    onEvent(ContactListEvent.DismissContact)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    error: String?,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChange,
            shape = RoundedCornerShape(20.dp)
        )

        error?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}