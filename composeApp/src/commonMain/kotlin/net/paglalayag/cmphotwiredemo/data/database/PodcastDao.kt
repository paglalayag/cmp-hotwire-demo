package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PodcastDao {
    @Upsert
    suspend fun upsert(podcast: PodcastEntity)

    @Query("DELETE FROM PodcastEntity WHERE episodeUrl = :episodeUrl")
    suspend fun deleteFromFavorites(episodeUrl: String)

    @Query("SELECT * FROM PodcastEntity")
    fun getFavoritePodcasts(): Flow<List<PodcastEntity>>
}