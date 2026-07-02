package edu.metrostate.ics342.mediatracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class MediaStatus(val label: String, val containerColor: Color, val contentColor: Color) {
    WANT_TO("Want To", Color(0xFF7C3AED), Color.White), // Custom color from spec
    IN_PROGRESS("In Progress", Color(0xFF2563EB), Color.White),
    FINISHED("Finished", Color(0xFF059669), Color.White)
}

@Composable
fun StatusBadge(status: MediaStatus, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(status.containerColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.label,
            style = MaterialTheme.typography.labelSmall,
            color = status.contentColor
        )
    }
}