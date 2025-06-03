package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PodcastEntity::class],
    version = 1
)
abstract class PodcastDatabase: RoomDatabase() {
    abstract val podcastDao: PodcastDao

    companion object {
        const val DB_NAME = "podcast.db"
    }
}