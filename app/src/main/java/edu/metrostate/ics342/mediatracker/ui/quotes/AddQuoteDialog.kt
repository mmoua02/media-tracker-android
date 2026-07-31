package edu.metrostate.ics342.mediatracker.ui.quotes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
reusable component
 */
@Composable
fun AddQuoteDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int?, Boolean) -> Unit,
    isSaving: Boolean = false
) {
    var text by remember { mutableStateOf("") }
    var pageNumber by remember { mutableStateOf("") }
    var isPublic by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Quote") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = text,
                    // max of 500 characters
                    onValueChange = { if (it.length <= 500) text = it },
                    label = { Text("Quote Text (required)") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5,
                    supportingText = { Text("${text.length}/500") }
                )
                OutlinedTextField(
                    value = pageNumber,
                    onValueChange = { if (it.all { char -> char.isDigit() }) pageNumber = it },
                    label = { Text("Page Number (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Public Quote")
                    // use switch to toggle between public/private
                    Switch(
                        checked = isPublic,
                        onCheckedChange = { isPublic = it }
                    )
                }
            }
        },
        // save button
        confirmButton = {
            Button(
                onClick = { onConfirm(text, pageNumber.toIntOrNull(), isPublic) },
                enabled = text.isNotBlank() && !isSaving
            ) {
                if (isSaving) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                } else {
                    Text("Save")
                }
            }
        },
        // cancel button
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !isSaving) {
                Text("Cancel")
            }
        }
    )
}
