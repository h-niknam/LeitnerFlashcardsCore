package com.nikapps.leitnerflashcardsmvp.data.repository

import com.nikapps.leitnerflashcardsmvp.data.cache.FlashCardDaoService
import com.nikapps.leitnerflashcardsmvp.data.model.Resource
import com.nikapps.leitnerflashcardsmvp.data.model.networkBoundResources
import com.nikapps.leitnerflashcardsmvp.data.network.FirebaseService
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashcardBackupStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlashCardRepository @Inject constructor(
    private val flashCardDaoService: FlashCardDaoService,
    private val firebaseService: FirebaseService
) {

    suspend fun getFlashCardsFromServer() {
        val cards = firebaseService.getFlashCards()
        cards.forEach {
            flashCardDaoService.insertFlashCard(it)
        }
    }

    fun getFlashCards(
        boxNumber: Int,
        shouldFetch: Boolean = true
    ): Flow<Resource<List<FlashCard>>> {
        return networkBoundResources(
            query = { flashCardDaoService.getFlashcardsByBox(boxNumber) },
            fetch = {
                firebaseService.getFlashCards()
            },
            saveFetched = {
                it.forEach {
                    flashCardDaoService.insertFlashCard(it)
                }
            },
            shouldFetch = { shouldFetch }
        )

    }

    suspend fun insertFlashCard(data: FlashCard): Boolean {

        val insertedId = flashCardDaoService.insertFlashCard(data)
        if (insertedId > 0) {
            data.id = insertedId.toInt()

            flashCardDaoService.updateFlashCard(data)

            val result = firebaseService.insertFlashCard(data)

            if (!result) {
                data.status = FlashcardBackupStatus.update
                flashCardDaoService.updateFlashCard(data)
            }

        } else {
            flashCardDaoService.deleteFlashCard(data)
        }

        return true
    }


    suspend fun deleteFlashCard(data: FlashCard) {

        val effectedRows = flashCardDaoService.deleteFlashCard(data)
        if (effectedRows == 1) {
            val result = firebaseService.deleteFlashCard(data)

            if (!result) {
                data.status = FlashcardBackupStatus.delete
                flashCardDaoService.insertFlashCard(data)
            }
        }
    }

    suspend fun updateFlashCard(data: FlashCard): Int {
        val effectedRows = flashCardDaoService.updateFlashCard(data)
        if (effectedRows == 1) {
            val result = firebaseService.insertFlashCard(data)
            if (!result) {
                data.status = FlashcardBackupStatus.update
                flashCardDaoService.updateFlashCard(data)
            }
        } else {
        }
        return effectedRows
    }


    fun getBoxFlashCardsCount(): Flow<List<Int>> {
        return flashCardDaoService.getFlashCardsCount()
    }

    fun getToKnowCount(): Flow<Int> = flashCardDaoService.getToKnowCount()
    fun getKnownCount(): Flow<Int> = flashCardDaoService.getKnownCount()
    fun getLearnedCount(): Flow<Int> = flashCardDaoService.getLearnedCount()

    fun getStudyFlashCards() = flashCardDaoService.getStudyFlashCards()


    suspend fun insertDeletedFlashCards() {
        //read deleted from database -> put into firebase deleted

        val deletedFromDB = flashCardDaoService.getDeletedFlashCards()
        val result = firebaseService.insertDeletedFlasgCards(deletedFromDB)
        if (result) {
            flashCardDaoService.deleteDeletedFlashcards()
        }
    }

    suspend fun updateUpdatedFlashCards() {
        //read update values from database -> update in firebase

        val updatedInDB = flashCardDaoService.getUpdatedFlashCards()
        val result = firebaseService.insertFlashCards(updatedInDB)
        if (result) {
            updatedInDB.forEach {
                it.status = FlashcardBackupStatus.nothing
                flashCardDaoService.updateFlashCard(it)
            }
        }
    }

    suspend fun syncDeletedFlashCards() {
        //read deleted from firebase, delete from db
        val deletedFromFB = firebaseService.getDeletedFlashCards()
        deletedFromFB.forEach {
            flashCardDaoService.deleteFlashCard(it)
        }
    }

}