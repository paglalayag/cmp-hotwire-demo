package net.paglalayag.cmphotwiredemo.data.mappers

import net.paglalayag.cmphotwiredemo.data.database.PodcastEntity
import net.paglalayag.cmphotwiredemo.domain.Podcast

fun Podcast.toPodcastEntity(): PodcastEntity {
    return PodcastEntity(
        episodeUrl = episodeUrl
    )
}