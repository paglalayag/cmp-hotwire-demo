package net.paglalayag.cmphotwiredemo.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<PodcastDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(PodcastDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}