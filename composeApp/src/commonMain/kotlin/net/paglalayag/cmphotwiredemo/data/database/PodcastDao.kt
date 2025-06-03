package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PodcastDao {
    @Query("SELECT * FROM PodcastEntity WHERE episodeUrl = :episodeUrl")
    suspend fun isEpisodeFavorite(episodeUrl: String): PodcastEntity?

    @Upsert
    suspend fun upsert(podcast: PodcastEntity)

    @Query("DELETE FROM PodcastEntity WHERE episodeUrl = :episodeUrl")
    suspend fun deleteFromFavorites(episodeUrl: String)
}