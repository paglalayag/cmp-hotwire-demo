package net.paglalayag.cmphotwiredemo.domain

import kotlinx.coroutines.flow.Flow
import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result
import net.paglalayag.cmphotwiredemo.data.database.PodcastEntity

interface PodcastRepository {
    fun isEpisodeFavorite(episodeUrl: String): Flow<Boolean>
    fun getFavoritePodcasts(): Flow<List<Podcast>>
    suspend fun markAsFavorite(episodeUrl: String, episodeDuration: Long) : Result<Unit, DataError.Local>
    suspend fun deleteFromFavorites(episodeUrl: String)
    suspend fun getLocalAudioFilePath(episodeUrl: String): String
}
