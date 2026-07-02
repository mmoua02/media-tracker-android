package edu.metrostate.ics342.mediatracker.data.model

import com.google.firebase.firestore.auth.User

data class AuthResponse (
    val user: User
)