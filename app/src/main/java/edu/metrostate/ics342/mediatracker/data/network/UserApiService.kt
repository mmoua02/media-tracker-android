package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.network.RegisterRequest
import edu.metrostate.ics342.mediatracker.data.model.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("users")
    suspend fun createUser(@Body body: RegisterRequest): Response<Unit>
}