package edu.metrostate.ics342.mediatracker.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
data transfer object
 */
@Serializable
data class QuoteRequest(
    @SerialName("media_id")
    val mediaId: Int,
    val text: String,
    @SerialName("page_number")
    val pageNumber: Int? = null,
    @SerialName("is_public")
    val isPublic: Boolean = true
)
