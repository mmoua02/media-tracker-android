package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.model.Quote
import retrofit2.HttpException

data class QuotePage(
    val items: List<Quote>,
    val nextCursor: String?,
    val hasMore: Boolean
)

class QuoteRepository(sessionRepository: SessionRepository) {
    private val api = RetrofitInstance.quoteApiService(sessionRepository)

    suspend fun getQuotes(public: Boolean? = null, after: String? = null): QuotePage {
        val response = api.getQuotes(public = public, after = after)
        val items = response.body() ?: emptyList()
        val nextCursor = response.headers()["X-Next-Cursor"]
        val hasMore = response.headers()["X-Has-More"] == "true"
        return QuotePage(items, nextCursor, hasMore)
    }

    suspend fun createQuote(
        mediaId: Int,
        text: String,
        pageNumber: Int?,
        isPublic: Boolean
    ): Result<Quote> = runCatching {
        val request = QuoteRequest(mediaId, text, pageNumber, isPublic)
        val response = api.createQuote(request)
        if (response.isSuccessful) {
            response.body() ?: throw IllegalStateException("Empty response body")
        } else {
            throw HttpException(response)
        }
    }

    suspend fun updateQuote(
        id: Int,
        mediaId: Int,
        text: String,
        pageNumber: Int?,
        isPublic: Boolean
    ): Result<Quote> = runCatching {
        val request = QuoteRequest(mediaId, text, pageNumber, isPublic)
        val response = api.updateQuote(id, request)
        if (response.isSuccessful) {
            response.body() ?: throw IllegalStateException("Empty response body")
        } else {
            throw HttpException(response)
        }
    }

    suspend fun deleteQuote(id: Int): Result<Unit> = runCatching {
        val response = api.deleteQuote(id)
        if (!response.isSuccessful) {
            throw HttpException(response)
        }
    }

    suspend fun likeQuote(id: Int): Result<Unit> = runCatching {
        val response = api.likeQuote(id)
        if (!response.isSuccessful && response.code() != 409) {
            throw HttpException(response)
        }
    }

    suspend fun unlikeQuote(id: Int): Result<Unit> = runCatching {
        val response = api.unlikeQuote(id)
        if (!response.isSuccessful) {
            throw HttpException(response)
        }
    }
}
