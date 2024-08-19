package cg.tutorials.tutionflow.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cg.tutorials.tutionflow.BatchData
import cg.tutorials.tutionflow.interfaces.BatchDaoInterface

@Database(entities = [BatchData::class], version = 1, exportSchema = false)
abstract class TutionDatabase : RoomDatabase(){
    abstract fun batchInterface(): BatchDaoInterface
    companion object {
        @Volatile
        private var tutionDatabase: TutionDatabase? = null
        fun getInstance(context: Context): TutionDatabase {
            if (tutionDatabase == null) {
                tutionDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    TutionDatabase::class.java,
                    "PersonDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return tutionDatabase!!
        }
    }
}