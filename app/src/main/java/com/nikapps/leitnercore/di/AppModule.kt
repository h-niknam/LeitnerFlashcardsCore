package com.nikapps.leitnerflashcardsmvp.di

import android.content.Context
import android.speech.tts.TextToSpeech
import com.nikapps.leitnerflashcardsmvp.domain.util.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    //SuperVisorJon() -> if one child fails, keep the other one
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @Provides
    @Singleton
    fun provideDateFormat(): SimpleDateFormat {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        sdf.timeZone = TimeZone.getTimeZone("gmt") //match the firebase
        return sdf
    }

    @Provides
    @Singleton
    fun provideDateUtils(dateFormatter: SimpleDateFormat): DateUtil {
        return DateUtil(
            dateFormatter
        )
    }

    @Singleton
    @Provides
    fun provideTextToSpeech(
        @ApplicationContext context: Context
    ) : TextToSpeech {
        val tts = TextToSpeech(
            context){
            if(it != TextToSpeech.ERROR) {

            }
        }
        tts.setLanguage(Locale.US)
        return tts
    }

}