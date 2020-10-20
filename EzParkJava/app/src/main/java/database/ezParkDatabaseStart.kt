package database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ezPark::class], version = 1, exportSchema = false)
abstract class ezParkDb : RoomDatabase() {
    companion object {
        fun get(application: Application) : ezParkDb {
            return Room.databaseBuilder(application, ezParkDb::class.java, "ezParkDB").build()
        }
    }

    abstract fun getEzParkDao(): ezParkDao

}