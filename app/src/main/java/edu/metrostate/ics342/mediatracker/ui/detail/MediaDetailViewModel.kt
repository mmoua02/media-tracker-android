package edu.metrostate.ics342.mediatracker.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.fakeSearchResults
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.Instant

sealed class MediaDetailUiState {
    object Loading : MediaDetailUiState()
    data class Success(
        val media: Media,
        val libraryStatus: LibraryStatus? = null,
        val isFavorited: Boolean = false,
        val isAdded: Boolean = false,
        val isSaving: Boolean = false,
        val error: String? = null
    ) : MediaDetailUiState()
    data class Error (val message: String) : MediaDetailUiState()
}

class MediaDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionRepository = DefaultSessionRepository(application)
    private val api = RetrofitInstance.mediaApiService(sessionRepository)

    private val _uiState = MutableStateFlow<MediaDetailUiState>(MediaDetailUiState.Loading)
    val uiState : StateFlow<MediaDetailUiState> = _uiState.asStateFlow()

    private var currentMediaId: Int? = null

    fun loadMedia(mediaId: Int) {
        currentMediaId = mediaId
        viewModelScope.launch {
            // First check fake repository
            val fakeMedia = FakeMediaRepository.mediaList.find { it.id == mediaId }
                ?: fakeSearchResults.find { it.id == mediaId }

            if (fakeMedia != null) {
                // Reactively combine fake repo flows for this specific media
                combine(
                    FakeMediaRepository.libraryItems,
                    FakeMediaRepository.favoriteIds
                ) { library, favorites ->
                    val libraryItem = library.find { it.mediaId == mediaId }
                    val isFavorited = favorites.contains(mediaId)
                    
                    MediaDetailUiState.Success(
                        media = fakeMedia,
                        libraryStatus = libraryItem?.status,
                        isFavorited = isFavorited,
                        isAdded = libraryItem != null
                    )
                }.collect { newState ->
                    _uiState.value = newState
                }
                return@launch
            }

            // Fallback to real API (not reactive for now)
            _uiState.value = MediaDetailUiState.Loading
            try {
                val media = api.getMedia(mediaId)
                val libraryResponse = runCatching { api.getLibraryItem(mediaId) }.getOrNull()
                val libraryStatus = if (libraryResponse?.isSuccessful == true) {
                    libraryResponse.body()?.status
                } else null

                val favoriteResponse = runCatching { api.getFavoriteItem(mediaId) }.getOrNull()
                val isFavorited = favoriteResponse?.code() == 200

                _uiState.value = MediaDetailUiState.Success(
                    media = media,
                    libraryStatus = libraryStatus,
                    isFavorited = isFavorited,
                    isAdded = libraryStatus != null
                )
            } catch (e: Exception) {
                _uiState.value = MediaDetailUiState.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }

    fun addToLibrary(mediaId: Int, status: String = "want_to") {
        val currentState = _uiState.value as? MediaDetailUiState.Success ?: return
        val newStatus = LibraryStatus.fromString(status)
        
        // Update Fake Repository (UI will update automatically via the collector in loadMedia)
        FakeMediaRepository.addToLibrary(currentState.media, newStatus)

        // Also call API in background
        viewModelScope.launch {
            try {
                api.addToLibrary(mapOf("mediaId" to mediaId, "status" to status))
            } catch (e: Exception) {
                // Rollback if needed
            }
        }
    }

    fun toggleFavorite(mediaId: Int) {
        val currentState = _uiState.value as? MediaDetailUiState.Success ?: return
        val wasFavorited = currentState.isFavorited
        
        // Update Fake Repository (UI will update automatically)
        FakeMediaRepository.toggleFavorite(mediaId)

        viewModelScope.launch {
            try {
                if (wasFavorited) {
                    api.removeFromFavorites(mediaId)
                } else {
                    api.addToFavorites(mapOf("mediaId" to mediaId))
                }
            } catch (e: Exception) {
                // Rollback if needed
            }
        }
    }
}
