package edu.metrostate.ics342.mediatracker.ui.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionRepository = DefaultSessionRepository(application)
    private val api = RetrofitInstance.mediaApiService(sessionRepository)

    private val _libraryItems = MutableStateFlow<List<LibraryItem>>(emptyList())
    val libraryItems: StateFlow<List<LibraryItem>> = _libraryItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _filterState = MutableStateFlow(LibraryStatus.WANT_TO)
    val filterState: StateFlow<LibraryStatus> = _filterState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        viewModelScope.launch {
            FakeMediaRepository.libraryItems.collect { items ->
                _libraryItems.value = items
            }
        }
    }

    fun loadLibrary() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = api.getLibrary()
                if (response.isSuccessful) {
                }
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun removeItem(mediaId: Int) {
        FakeMediaRepository.removeFromLibrary(mediaId)

        viewModelScope.launch {
            try {
                api.removeFromLibrary(mediaId)
            } catch (e: Exception) {
            }
        }
    }

    fun updateStatus(mediaId: Int, newStatus: LibraryStatus) {
        // Update Fake Repository
        FakeMediaRepository.updateLibraryStatus(mediaId, newStatus)

        viewModelScope.launch {
            try {
                api.updateLibraryStatus(mediaId, mapOf("status" to newStatus.toApiString()))
            } catch (e: Exception) {
            }
        }
    }

    fun updateFilter(status: LibraryStatus) {
        _filterState.value = status
    }
}
