package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.User

interface SessionRepository {
    suspend fun saveSession(accessToken: String, refreshToken: String, user: User)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun getUser(): User?
    suspend fun clearSession()
}