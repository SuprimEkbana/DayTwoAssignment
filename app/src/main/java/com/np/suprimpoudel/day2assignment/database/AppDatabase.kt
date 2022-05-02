package com.np.suprimpoudel.day2assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.np.suprimpoudel.day2assignment.features.shared.models.ContactDetails
import com.np.suprimpoudel.day2assignment.utils.constants.DatabaseConstants

@Database(entities = [(ContactDetails::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DatabaseConstants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
