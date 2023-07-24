package com.example.contactcomposemultiplatform.contact.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactcomposemultiplatform.contact.domain.Contact

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    contact: Contact
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactPhoto(
            modifier = Modifier
                .size(50.dp),
            contact = contact
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            modifier = Modifier
                .weight(1f),
            text = "${contact.firstName} ${contact.lastName}"
        )
    }
}