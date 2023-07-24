package com.example.contactcomposemultiplatform.contact.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactcomposemultiplatform.contact.domain.Contact

@Composable
fun ContactPreviewItem(
    modifier: Modifier = Modifier,
    contact: Contact,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ContactPhoto(
            modifier = Modifier
                .size(50.dp),
            contact = contact
        )

        Text(
            modifier = Modifier,
            text = contact.firstName
        )
    }
}