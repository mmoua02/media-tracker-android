package edu.metrostate.ics342.mediatracker.ui.quotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.Quote
import edu.metrostate.ics342.mediatracker.data.network.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class QuoteUiState {
    data object Loading : QuoteUiState()
    data class Success(val quotes: List<Quote>) : QuoteUiState()
    data class Error(val message: String) : QuoteUiState()
}

class QuoteViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionRepository = DefaultSessionRepository(application)
    private val quoteRepository = QuoteRepository(sessionRepository)

    private val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    private val _currentFilterPublic = MutableStateFlow<Boolean?>(null)
    val currentFilterPublic: StateFlow<Boolean?> = _currentFilterPublic.asStateFlow()

    init {
        loadQuotes()
    }

    fun setFilter(public: Boolean?) {
        _currentFilterPublic.value = public
        loadQuotes()
    }

    fun loadQuotes() {
        viewModelScope.launch {
            _uiState.value = QuoteUiState.Loading
            try {
                val quotePage = quoteRepository.getQuotes(public = _currentFilterPublic.value)
                _uiState.value = QuoteUiState.Success(quotePage.items)
            } catch (e: Exception) {
                _uiState.value = QuoteUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun deleteQuote(id: Int) {
        viewModelScope.launch {
            val result = quoteRepository.deleteQuote(id)
            result.onSuccess {
                loadQuotes()
            }
        }
    }

    fun toggleLike(quote: Quote) {
        viewModelScope.launch {
            val result = if (quote.isLiked) {
                quoteRepository.unlikeQuote(quote.id)
            } else {
                quoteRepository.likeQuote(quote.id)
            }
            
            result.onSuccess {
                loadQuotes()
            }.onFailure { e ->

            }
        }
    }
}
