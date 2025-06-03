package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<PodcastDatabase>
}