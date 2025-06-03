package net.paglalayag.cmphotwiredemo.domain

import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result

interface PodcastRepository {
    suspend fun isEpisodeFavorite(episodeUrl: String): Boolean
    suspend fun markAsFavorite(episodeUrl: String) : Result<Unit, DataError.Local>
    suspend fun deleteFromFavorites(episodeUrl: String)
}
