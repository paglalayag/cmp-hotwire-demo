package net.paglalayag.cmphotwiredemo.domain

data class PodcastsState(
    val episodeUrl: String = "",
    val episodeDuration: Long = 0L,
    val isFavorite: Boolean = false
)
