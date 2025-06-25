package net.paglalayag.cmphotwiredemo.domain

import net.paglalayag.cmphotwiredemo.core.presentation.UiText

data class PodcastsState(
    val episodeUrl: String = "",
    val episodeDuration: Long = 0L,
    val episodeAudiofile: String = "",
    val currentTime: Long = 0L,
    val isFavorite: Boolean = false,
    val favoritePodcasts: List<Podcast> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)
