package com.nikapps.leitnerflashcardsmvp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nikapps.leitnerflashcardsmvp.data.cache.entity.FlashCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [FlashCardEntity::class], version = 1)
//@TypeConverters(TypeConvertor::class)
abstract class FlashCardDatabase : RoomDatabase() {

    abstract fun flashCardDao(): FlashCardDAO

    class Callback @Inject constructor(
        private val database: Provider<FlashCardDatabase>,
        private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().flashCardDao()

            applicationScope.launch {
//                dao.insertFlashCard(FlashCardEntity(front = "font 1", back = "back1"))
//                dao.insertFlashCard(FlashCardEntity(front = "font 2", back = "back2"))
//                dao.insertFlashCard(FlashCardEntity(front = "font 3", back = "back3"))
//                dao.insertFlashCard(FlashCardEntity(front = "font 4", back = "back4"))
            }

        }
    }
}