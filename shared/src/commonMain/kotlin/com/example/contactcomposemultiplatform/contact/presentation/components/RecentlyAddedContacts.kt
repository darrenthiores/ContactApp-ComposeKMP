package com.example.contactcomposemultiplatform.contact.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contactcomposemultiplatform.contact.domain.Contact

@Composable
fun RecentlyEditContacts(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    onClick: (Contact) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        if (contacts.isNotEmpty()) {
            Text(
                text = "Recently Added",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = contacts,
                key = { contact -> contact.id ?: contact.phoneNumber }
            ) { contact ->
                ContactPreviewItem(
                    modifier = Modifier,
                    contact = contact,
                    onClick = {
                        onClick(contact)
                    }
                )
            }
        }
    }
}