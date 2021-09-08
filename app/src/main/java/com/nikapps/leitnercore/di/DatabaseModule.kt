package com.nikapps.leitnerflashcardsmvp.di

import android.content.Context
import androidx.room.Room
import com.nikapps.leitnerflashcardsmvp.data.cache.FlashCardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFlashCardDatabase(
        @ApplicationContext context: Context,
        callback: FlashCardDatabase.Callback
    ): FlashCardDatabase = Room.databaseBuilder(
        context,
        FlashCardDatabase::class.java, "database-name"
    ).allowMainThreadQueries()
        .addCallback(callback)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideFlashCardDAO(database: FlashCardDatabase) = database.flashCardDao()


}