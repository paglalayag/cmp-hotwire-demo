package net.paglalayag.cmphotwiredemo.domain

data class Podcast(
    val episodeUrl: String,
    var audioFilePath: String?,
    val duration: Long
)
