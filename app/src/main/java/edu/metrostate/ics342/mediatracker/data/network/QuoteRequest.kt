package edu.metrostate.ics342.mediatracker.data.network

import kotlinx.serialization.Serializable

/*
data transfer object
 */
@Serializable
data class QuoteRequest(
    val mediaId: Int,
    val text: String,
    val pageNumber: Int? = null,
    val isPublic: Boolean = true
)
