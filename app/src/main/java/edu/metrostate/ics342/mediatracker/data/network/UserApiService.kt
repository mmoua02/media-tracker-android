package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.TokenResponse
import edu.metrostate.ics342.mediatracker.data.network.RegisterRequest
import edu.metrostate.ics342.mediatracker.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApiService {

    @POST("users")
    suspend fun createUser(@Body body: RegisterRequest): Response<Unit>

    @POST("tokens")
    suspend fun login(@Body body: LoginRequest): Response<AuthResponse>

    @FormUrlEncoded
    @POST("tokens")
    suspend fun getTokens(
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("username") email: String,
        @Field("password") password: String
    ): Response<TokenResponse>
}