package edu.metrostate.ics342.mediatracker.data.network

import kotlinx.serialization.Serializable

@Serializable
class RegisterRequest (

        val email: String,
        val password: String,
        val username: String,
        val displayName: String,
        val clientId: String,
        val clientSecret: String
)