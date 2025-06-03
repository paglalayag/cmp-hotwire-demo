package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PodcastEntity(
    @PrimaryKey(autoGenerate = false) val episodeUrl: String
)