package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PodcastDatabaseConstructor: RoomDatabaseConstructor<PodcastDatabase> {
    override fun initialize(): PodcastDatabase
}