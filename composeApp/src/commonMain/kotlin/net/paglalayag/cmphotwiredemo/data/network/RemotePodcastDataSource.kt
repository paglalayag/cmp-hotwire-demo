package net.paglalayag.cmphotwiredemo.data.network

import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result

interface RemotePodcastDataSource {
    suspend fun getPodcastAudio(
        episodeUrl: String
    ): Result<ByteArray, DataError.Remote>
}