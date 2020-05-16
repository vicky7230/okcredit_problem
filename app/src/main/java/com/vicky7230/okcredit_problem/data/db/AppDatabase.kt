package com.vicky7230.okcredit_problem.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vicky7230.okcredit_problem.data.Article
import com.vicky7230.okcredit_problem.data.db.dao.ArticleDao

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    // Function to create object of Room Database
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}