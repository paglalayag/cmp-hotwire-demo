package net.paglalayag.cmphotwiredemo.data.repository

import androidx.sqlite.SQLiteException
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.createDirectories
import io.github.vinceglb.filekit.delete
import io.github.vinceglb.filekit.exists
import io.github.vinceglb.filekit.filesDir
import io.github.vinceglb.filekit.list
import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.write
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result
import net.paglalayag.cmphotwiredemo.core.domain.onError
import net.paglalayag.cmphotwiredemo.core.domain.onSuccess
import net.paglalayag.cmphotwiredemo.data.database.PodcastDao
import net.paglalayag.cmphotwiredemo.data.database.PodcastEntity
import net.paglalayag.cmphotwiredemo.data.mappers.toPodcast
import net.paglalayag.cmphotwiredemo.data.network.RemotePodcastDataSource
import net.paglalayag.cmphotwiredemo.domain.Podcast
import net.paglalayag.cmphotwiredemo.domain.PodcastRepository

class DefaultPodcastRepository(
    private val remotePodcastDataSource: RemotePodcastDataSource,
    private val podcastDao: PodcastDao
): PodcastRepository {
    val parentDir = PlatformFile("${FileKit.filesDir.path}/podcasts")

    override  fun isEpisodeFavorite(episodeUrl: String): Flow<Boolean> {
        println("checking isEpisodeFavorite from DefaultRepository: ${episodeUrl}")

        return podcastDao
            .getFavoritePodcasts()
            .map { podcastEntities ->
                podcastEntities.any {
                    it.episodeUrl == episodeUrl
                }
            }
    }

    override suspend fun markAsFavorite(
        episodeUrl: String,
        episodeDuration: Long
    ): Result<Unit, DataError.Local> {
        return try {
            val audioFilePath = episodeUrl.substringAfterLast("/")

            println("inside markAsFavorite repo, $episodeUrl")

            if (!parentDir.exists()) {
                parentDir.createDirectories()
            }

            val savePath = PlatformFile(parentDir, audioFilePath)

            val fetchResult = remotePodcastDataSource
                .getPodcastAudio(episodeUrl)

            fetchResult.onSuccess { audioFile ->
                println("fetch successful")
                savePath.write(audioFile)
            }
            fetchResult.onError { error ->
                println("fetch error: $error")
            }

            println("after save, filesDir contains ${parentDir.list()}")

            val podcastEntity = PodcastEntity(
                episodeUrl = episodeUrl,
                audioFilePath = audioFilePath,
                duration = episodeDuration
            )

            podcastDao.upsert(podcastEntity)

            println("after upsert, ${isEpisodeFavorite(episodeUrl)}")

            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(episodeUrl: String) {
        val audioFilePath = episodeUrl.substringAfterLast("/")

        println("inside deleteFromFavorites repo, ${episodeUrl}")

        val savePath = PlatformFile(parentDir, audioFilePath)
        savePath.delete()

        println("after delete, filesDir contains ${parentDir.list()}")

        podcastDao.deleteFromFavorites(episodeUrl)

        println("after delete, ${isEpisodeFavorite(episodeUrl)}")
    }

    override suspend fun getLocalAudioFilePath(episodeUrl: String): String {
        val audioFilePath = episodeUrl.substringAfterLast("/")

        println("should be saved locally, filesDir contains ${parentDir.list()}")
        return "${parentDir}/${audioFilePath}"
    }

    override fun getFavoritePodcasts(): Flow<List<Podcast>> {
        return podcastDao
            .getFavoritePodcasts()
            .map { podcastEntities ->
                podcastEntities.map { it.toPodcast() }
            }
    }
}