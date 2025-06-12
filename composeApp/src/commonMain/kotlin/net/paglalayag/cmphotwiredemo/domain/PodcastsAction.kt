package net.paglalayag.cmphotwiredemo.domain

sealed interface PodcastsAction {
    data class SetEpisodeURL(val episodeUrl: String, val episodeDuration: String): PodcastsAction
    data class ToggleEpisodeURL(val episodeUrl: String): PodcastsAction
    data class IsEpisodeFavorite(val episodeUrl: String): PodcastsAction
}