package net.paglalayag.cmphotwiredemo.domain

sealed interface PodcastsAction {
    data class SetEpisodeURL(val episodeUrl: String, val episodeDuration: String): PodcastsAction
}