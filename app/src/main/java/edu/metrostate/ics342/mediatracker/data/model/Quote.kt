package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.SerialName
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
    @SerialName("media_id")
    val mediaId: Int,
    val text: String,
    @SerialName("page_number")
    val pageNumber: Int? = null,
    @SerialName("is_public")
    val isPublic: Boolean = true,
    @SerialName("user_id")
    val userId: String,
    @SerialName("is_liked")
    val isLiked: Boolean = false,
    @SerialName("like_count")
    val likeCount: Int = 0,
    val media: Media? = null
)
