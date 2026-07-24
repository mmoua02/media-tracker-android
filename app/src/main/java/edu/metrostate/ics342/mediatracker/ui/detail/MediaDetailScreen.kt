package edu.metrostate.ics342.mediatracker.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit

@Composable
fun MediaDetailScreen(
    mediaId: Int,
    onNavigateBack: () -> Unit,
    onWriteReview: (Int) -> Unit,
    viewModel: MediaDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(mediaId) {
        viewModel.loadMedia(mediaId)
    }

    LaunchedEffect(uiState) {
        val state = uiState
        if (state is MediaDetailUiState.Success && state.error != null) {
            snackbarHostState.showSnackbar(state.error)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (val state = uiState) {
                is MediaDetailUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is MediaDetailUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(state.message, color = MaterialTheme.colorScheme.error)
                            Spacer(Modifier.height(8.dp))
                            Button(onClick = { viewModel.loadMedia(mediaId) }) {
                                Text("Retry")
                            }
                        }
                    }
                }
                is MediaDetailUiState.Success -> {
                    MediaDetailContent(
                        media = state.media,
                        isAdded = state.isAdded,
                        isFavorited = state.isFavorited,
                        isSaving = state.isSaving,
                        onNavigateBack = onNavigateBack,
                        onWriteReview = { onWriteReview(mediaId) },
                        onAddToLibrary = { viewModel.addToLibrary(mediaId) },
                        onToggleFavorite = { viewModel.toggleFavorite(mediaId) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailContent(
    media: Media,
    isAdded: Boolean,
    isFavorited: Boolean,
    isSaving: Boolean,
    onNavigateBack: () -> Unit,
    onWriteReview: () -> Unit,
    onAddToLibrary: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawVerticalScrollbar(scrollState)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Cover Image")
                // TODO: Use Coil to load media.coverUrl
            }
        }

        // title and credit
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(media.title, style = MaterialTheme.typography.headlineMedium)
            Text(media.creatorCredit(LocalContext.current), style = MaterialTheme.typography.bodyLarge)
        }

        // rating row
        Row(verticalAlignment = Alignment.CenterVertically) {
            StarRating(rating = media.averageRating.toInt())
            Spacer(Modifier.width(8.dp))
            Text(" ${"%.1f".format(media.averageRating)} ", fontWeight = FontWeight.Bold)
            Text("(${media.ratingCount})")
        }

        // action buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onAddToLibrary,
                modifier = Modifier.weight(1f),
                enabled = !isAdded && !isSaving,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAdded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary,
                    contentColor = if (isAdded) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
                )
            ) {
                if (isSaving && !isAdded) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    if (isAdded) {
                        Icon(Icons.Default.Check, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("In Library")
                    } else {
                        Text("+ Want To")
                    }
                }
            }

            OutlinedButton(
                onClick = onToggleFavorite,
                modifier = Modifier.weight(1f),
                enabled = !isSaving,
                colors = if (isFavorited) {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors()
                }
            ) {
                Icon(
                    imageVector = if (isFavorited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorited) Color.Red else MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(8.dp))
                Text(if (isFavorited) "Saved" else "Save")
            }
        }

        // about
        Text(
            text = "About",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = media.description ?: "No description available.",
            modifier = Modifier.align(Alignment.Start)
        )

        // stat grid
        val middleStat = when (media.mediaType) {
            "book" -> "${media.pageCount ?: 0} pages"
            "movie" -> "${media.runtimeMinutes ?: 0} min"
            "show" -> "${media.seasonCount ?: 0} seasons"
            else -> ""
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatBox(media.publishedYear?.toString() ?: "N/A")
            StatBox(middleStat)
            StatBox(media.genres.firstOrNull() ?: "N/A")
        }

        // review header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Reviews (${media.reviewCount})", style = MaterialTheme.typography.titleMedium)
            TextButton(onClick = onWriteReview) { Text("+ Write Review") }
        }

        // Placeholder for reviews
        ReviewCard(
            username = "@alice_reads",
            rating = 4,
            date = "2 days ago",
            content = "A timeless classic. Fresh every time."
        )
        ReviewCard(
            username = "@bob_scifi",
            rating = 5,
            date = "1 week ago",
            content = "One of the best in the genre. Highly recommend."
        )
        ReviewCard(
            username = "@charlie_bookworm",
            rating = 3,
            date = "2 weeks ago",
            content = "Interesting concepts, but a bit slow in the middle."
        )
    }
}

@Composable
fun ReviewCard(username: String, rating: Int, date: String, content: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(username, fontWeight = FontWeight.Bold)
            StarRating(rating = rating)
            Text(date, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(8.dp))
            Text(content)
        }
    }
}

fun Modifier.drawVerticalScrollbar(
    scrollState: ScrollState,
    color: Color = Color.LightGray
): Modifier = drawWithContent {
    drawContent()

    val viewPortHeight = size.height
    val totalContentHeight = viewPortHeight + scrollState.maxValue

    if (totalContentHeight > viewPortHeight) {
        val scrollBarHeight = (viewPortHeight / totalContentHeight) * viewPortHeight
        val scrollBarOffset = (scrollState.value.toFloat() / totalContentHeight) * viewPortHeight

        drawRoundRect(
            color = color.copy(alpha = 0.5f),
            topLeft = Offset(size.width - 6.dp.toPx(), scrollBarOffset),
            size = Size(4.dp.toPx(), scrollBarHeight),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
    }
}

@Composable
fun StatBox(text: String) {
    Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surfaceVariant) {
        Text(text, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.labelLarge)
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
    }
}
