package net.paglalayag.cmphotwiredemo.data.repository

import androidx.sqlite.SQLiteException
import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result
import net.paglalayag.cmphotwiredemo.data.database.PodcastDao
import net.paglalayag.cmphotwiredemo.data.mappers.toPodcastEntity
import net.paglalayag.cmphotwiredemo.domain.Podcast
import net.paglalayag.cmphotwiredemo.domain.PodcastRepository

class DefaultPodcastRepository(
    private val podcastDao: PodcastDao
): PodcastRepository {
    override suspend fun isEpisodeFavorite(episoderUrl: String): Boolean {
        if(podcastDao.isEpisodeFavorite(episoderUrl) != null) {
            return true
        } else {
            return false
        }
    }
    override suspend fun markAsFavorite(episodeUrl: String): Result<Unit, DataError.Local> {
        val podcast = Podcast(
            episodeUrl = episodeUrl
        )
        return try {
            println("inside markAsFavorite repo, $podcast")
            podcastDao.upsert(podcast.toPodcastEntity())
            println("after upsert, ${podcastDao.isEpisodeFavorite(episodeUrl)}")

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(episodeUrl: String) {
        println("inside deleteFromFavorites repo, $episodeUrl")
        podcastDao.deleteFromFavorites(episodeUrl)
        println("after delete, ${podcastDao.isEpisodeFavorite(episodeUrl)}")
    }
}