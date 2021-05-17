package br.com.fausto.institutions_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fausto.institutions_app.model.University
import br.com.fausto.institutions_app.util.Converter

@Database(entities = [University::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val universityDao: UniversityDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "university.db"
            )
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}