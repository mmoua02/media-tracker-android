package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse (
    val accessToken: String,
    val refreshToken: String,
    @Contextual val user: User
)