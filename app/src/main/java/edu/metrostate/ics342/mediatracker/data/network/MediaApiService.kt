package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.Media
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApiService {
    @GET("media")
    suspend fun searchMedia(
        @Query("query") query: String? = null,
        @Query("type") type: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ): Response<List<Media>>

    @GET("media/{id}")
    suspend fun getMedia(@Path("id") id: Int): Media

    @GET("library/{mediaId}")
    suspend fun getLibraryItem(@Path("mediaId") mediaId: Int): Response<LibraryItem>

    @POST("library")
    suspend fun addToLibrary(@Body body: Map<String, Int>): Response<Unit>
}
