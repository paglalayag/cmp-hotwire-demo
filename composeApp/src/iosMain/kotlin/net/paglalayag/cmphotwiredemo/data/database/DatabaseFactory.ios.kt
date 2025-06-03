@file: OptIn(ExperimentalForeignApi::class)
package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSFileManager

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<PodcastDatabase> {
        val dbFile = documentDirectory() + "/${PodcastDatabase.DB_NAME}"
        return Room.databaseBuilder<PodcastDatabase>(
            name = dbFile
        )
    }

    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)
    }
}