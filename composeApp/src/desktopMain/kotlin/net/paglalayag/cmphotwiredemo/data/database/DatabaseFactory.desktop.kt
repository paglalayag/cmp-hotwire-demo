package net.paglalayag.cmphotwiredemo.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<PodcastDatabase> {
        val os = System.getProperty("os_name").lowercase()
        val userHome = System.getProperty("user.name")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "CMPHotwireDemo")
            os.contains("mac") -> File(userHome, "Library/Application Support/CMPHotwireDemo")
            else -> File(userHome, ".local/share/CMPHotwireDemo")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, PodcastDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}