package br.com.lugedevelopment.clarochallenge.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class MoviesRoomDatabase : RoomDatabase(){

    abstract fun moviesDAO(): MoviesDAO

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.moviesDAO())
                }
            }
        }

        suspend fun populateDatabase(moviesDao: MoviesDAO) {
            moviesDao.removeAll()

            var movie = MovieEntity(1, "Filme 001", "Action")
            moviesDao.insertMovie(movie)
            movie = MovieEntity(2, "Filme 002", "Action 01")
            moviesDao.insertMovie(movie)
            movie = MovieEntity(3, "Filme 003", "Drama")
            moviesDao.insertMovie(movie)
            movie = MovieEntity(4, "Filme 004", "Suspense")
            moviesDao.insertMovie(movie)

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): MoviesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    "movies_database"
                )
                    .addCallback(MovieDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

