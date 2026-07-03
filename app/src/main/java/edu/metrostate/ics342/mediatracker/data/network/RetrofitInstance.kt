package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.SessionRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitInstance {

    private val json = Json {
        ignoreUnknownKeys   = true
        encodeDefaults      = true
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
    })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
    val mediaApiService: MediaApiService = retrofit.create(MediaApiService::class.java)

    fun mediaApiService(sessionRepository: SessionRepository): MediaApiService {
        val authClient = client.newBuilder()
            .addInterceptor(AuthInterceptor(sessionRepository))
            .build()
        return retrofit.newBuilder()
            .client(authClient)
            .build()
            .create(MediaApiService::class.java)
    }

}