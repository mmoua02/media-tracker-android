package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.Quote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface QuoteApiService {

    @GET("quotes") // GET request
    suspend fun getQuotes(
        @Query("public") public: Boolean? = null,
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ): Response<List<Quote>>

    @POST("quotes") // POST request to save new quote
    suspend fun createQuote(@Body request: QuoteRequest): Response<Quote>

    @PUT("quotes/{id}")
    suspend fun updateQuote(
        @Path("id") id: Int,
        @Body request: QuoteRequest
    ): Response<Quote>

    @DELETE("quotes/{id}")
    suspend fun deleteQuote(@Path("id") id: Int): Response<Unit>

    @POST("quotes/{id}/likes")
    suspend fun likeQuote(@Path("id") id: Int): Response<Unit>

    @DELETE("quotes/{id}/likes")
    suspend fun unlikeQuote(@Path("id") id: Int): Response<Unit>
}
