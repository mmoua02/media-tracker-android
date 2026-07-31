package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.model.Quote
import retrofit2.Response

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

    suspend fun createQuote(mediaId: Int, text: String, pageNumber: Int?, isPublic: Boolean): Response<Quote> {
        val request = QuoteRequest(mediaId, text, pageNumber, isPublic)
        return api.createQuote(request)
    }
}
