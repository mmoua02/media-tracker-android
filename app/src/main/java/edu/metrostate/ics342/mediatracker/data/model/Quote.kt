package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

/*
define data class for Quote object
need fields for:
text
page
privacy status
associated media
*/
@Serializable
data class Quote(
    val id: Int,
    val mediaId: Int,
    val text: String,
    val pageNumber: Int? = null,
    val isPublic: Boolean = true,
    val userId: Int,
    val media: Media? = null
)
