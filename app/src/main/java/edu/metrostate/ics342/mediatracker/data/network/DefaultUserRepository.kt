package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.repository.RegisterResult
import edu.metrostate.ics342.mediatracker.data.repository.UserRepository
import okio.IOException

class DefaultUserRepository (
    private val service: UserApiService = RetrofitInstance.userApiService
) : UserRepository {

    override suspend fun register (
        email: String,
        password: String,
        username: String,
        displayName: String
    ): RegisterResult {
        return try {
            val response = service.createUser(
                RegisterRequest(
                    email = email,
                    password = password,
                    username = username,
                    displayName = displayName,
                    clientId = ApiConstants.CLIENT_ID,
                    clientSecret = ApiConstants.CLIENT_SECRET
                )

            )

            when (response.code()) {
                201 -> RegisterResult.Success
                409 -> RegisterResult.Conflict
                else -> RegisterResult.UnknownError
            }
        } catch (e: IOException) {
            RegisterResult.NetworkError
        }
    }
}