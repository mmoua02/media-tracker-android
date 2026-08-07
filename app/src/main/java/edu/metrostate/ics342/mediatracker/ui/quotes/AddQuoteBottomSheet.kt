package edu.metrostate.ics342.mediatracker.ui.quotes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.metrostate.ics342.mediatracker.data.model.Quote
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuoteBottomSheet(
    mediaId: Int,
    mediaTitle: String,
    onDismiss: () -> Unit,
    quoteToEdit: Quote? = null,
    onSave: (String, Int?, Boolean) -> Unit
) {
    var quoteText by remember { mutableStateOf(quoteToEdit?.text ?: "") }
    var pageNum by remember { mutableStateOf(quoteToEdit?.pageNumber?.toString() ?: "") }
    var isPublicChecked by remember { mutableStateOf(quoteToEdit?.isPublic ?: false) }
    
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (quoteToEdit == null) "Add Quote to $mediaTitle" else "Edit Quote",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = quoteText,
                onValueChange = { if (it.length <= 500) quoteText = it },
                label = { Text("Quote Text") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                supportingText = { Text("${quoteText.length}/500") }
            )

            OutlinedTextField(
                value = pageNum,
                onValueChange = { if (it.all { char -> char.isDigit() }) pageNum = it },
                label = { Text("Page Number (optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Make Public")
                Switch(
                    checked = isPublicChecked,
                    onCheckedChange = { isPublicChecked = it }
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = quoteText.isNotBlank(),
                onClick = {
                    onSave(quoteText, pageNum.toIntOrNull(), isPublicChecked)
                    onDismiss()
                }
            ) {
                Text(if (quoteToEdit == null) "Save Quote" else "Update Quote")
            }
        }
    }
}
