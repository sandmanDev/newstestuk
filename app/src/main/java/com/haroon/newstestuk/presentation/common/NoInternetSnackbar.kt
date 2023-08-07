package com.haroon.newstestuk.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoInternetSnackbar(onClick: (() -> Unit)) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        action = {
            TextButton(onClick = { onClick() }
            ) {
                Text("Refresh", style = MaterialTheme.typography.bodyMedium)
            }
        }
    ) {
        Text(
            text = "No internet connection",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}