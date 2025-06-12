package net.paglalayag.cmphotwiredemo.data.network

import net.paglalayag.cmphotwiredemo.core.domain.DataError
import net.paglalayag.cmphotwiredemo.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import net.paglalayag.cmphotwiredemo.core.data.safeCall

class KtorRemotePodcastDataSource(
    private val httpClient: HttpClient
): RemotePodcastDataSource {
    override suspend fun getPodcastAudio(
        episodeUrl: String
    ): Result<ByteArray, DataError.Remote> {
        return  safeCall<ByteArray> {
            httpClient.get(
                urlString = episodeUrl
            )
        }
    }
}