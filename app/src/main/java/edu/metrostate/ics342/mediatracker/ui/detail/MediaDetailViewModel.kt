package edu.metrostate.ics342.mediatracker.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.fakeSearchResults
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MediaDetailUiState {
    object Loading : MediaDetailUiState()
    data class Success(
        val media: Media,
        val isAdded: Boolean,
        val isSaving: Boolean = false
    ) : MediaDetailUiState()
    data class Error (val message: String) : MediaDetailUiState()
}

class MediaDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionRepository = DefaultSessionRepository(application)
    private val api = RetrofitInstance.mediaApiService(sessionRepository)

    private val _uiState = MutableStateFlow<MediaDetailUiState>(MediaDetailUiState.Loading)
    val uiState : StateFlow<MediaDetailUiState> = _uiState.asStateFlow()

    fun loadMedia(mediaId: Int) {
        viewModelScope.launch {
            _uiState.value = MediaDetailUiState.Loading
            try {
                val media = api.getMedia(mediaId)
                val libraryResponse = api.getLibraryItem(mediaId)
                val isAdded = libraryResponse.code() == 200
                _uiState.value = MediaDetailUiState.Success(media, isAdded)
            } catch (e: Exception) {
                val fallbackMedia = FakeMediaRepository.mediaList.find { it.id == mediaId }
                    ?: fakeSearchResults.find { it.id == mediaId }
            }
        }
    }

    fun addToLibrary(mediaId: Int) {
        val currentState = _uiState.value as? MediaDetailUiState.Success ?: return

        // Disable button immediately to prevent double-tap
        _uiState.value = currentState.copy(isSaving = true)

        viewModelScope.launch {
            try {
                api.addToLibrary(mapOf("mediaId" to mediaId))
                _uiState.value = currentState.copy(isAdded = true, isSaving = false)
            } catch (e: Exception) {
                _uiState.value = currentState.copy(isAdded = true, isSaving = false)
            }
        }
    }
}
