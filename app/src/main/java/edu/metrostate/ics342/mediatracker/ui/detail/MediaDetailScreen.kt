package edu.metrostate.ics342.mediatracker.ui.detail

import android.media.Rating
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailScreen(
    mediaId: Int,
    onNavigateBack: () -> Unit,
    onWriteReview: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.MoreVert, "Menu")
            }
        }

        // cover image
        Surface(modifier = Modifier.fillMaxWidth().height(250.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Box(contentAlignment = Alignment.Center) { Text("Cover Image") }
        }

        // title and credit
        Text("Dune", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.align(
            Alignment.CenterHorizontally))
        Text("Written by Frank Herbert", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.align(
            Alignment.CenterHorizontally))

        // rating row
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            StarRating(rating = 4)
            Spacer(Modifier.width(8.dp))
            // The line blocked out provided one-star
            // Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Text(" 4.8 ", fontWeight = FontWeight.Bold)
            Text("(1,234)")
        }

        // action buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {  }, modifier = Modifier.weight(1f)) { Text("+ Want To") }

            OutlinedButton(onClick = {  }, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.FavoriteBorder, null)
                Spacer(Modifier.width(8.dp))
                Text("Save")
            }
        }

        // about
        Text("About", style = MaterialTheme.typography.titleMedium)
        Text("A noble family becomes embroiled in a war for control over the most valuable substance in the universe on the desert planet Arrakis...")

        // stat grid
        /*
        this block would be used for when there is a media object to pass this to
            // Example for a book
                val myMedia = Media(
                    year = "1954",
                    genre = "Fantasy",
                    type = MediaType.BOOK,
                    pageCount = 423
)

        val middleStat = when (media.type) {
            MediaType.BOOK -> "${media.pageCount} pages"
            MediaType.MOVIE -> "${media.runtime} min"
            MediaType.SHOW -> "${media.seasonCount} seasons"
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatBox(media.year)     // year
            StatBox(middleStat)     // type
            StatBox(media.genre)    // genre
        }
         */

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatBox("Year")
            StatBox("Type")
            StatBox("Genre")
        }

        // review header
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Reviews (42)", style = MaterialTheme.typography.titleMedium)

            TextButton(onClick = { onWriteReview(mediaId) }) { Text("+ Write Review") }
        }

        // fake reviews
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("@alice_reads", fontWeight = FontWeight.Bold)
                StarRating(rating = 4)
                Text("2 days ago", style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
                Text("A timeless classic. Fresh every time.")
            }
        }
        VerticalScrollBar(
            modifier = Modifier.align(Alignment.CenterEnd as Alignment.Horizontal).fillMaxHeight(),
            scrollState = scrollState
        )
    }
}

@Composable
fun VerticalScrollBar(modifier: Modifier, scrollState: ScrollState) {
    TODO("Not yet implemented")
}

@Composable
fun StatBox(text: String) {
    Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surfaceVariant) {
        Text(text, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun StarRating (rating: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Default.StarBorder,
                contentDescription = null,
                tint = if (i <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaDetailScreenPreview() {
    MaterialTheme {
        MediaDetailScreen(1, {}, {})
    }
}