package com.nikapps.leitnerflashcardsmvp.data.cache

import com.nikapps.leitnerflashcardsmvp.data.cache.mapper.FlashCardEntityMapper
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FlashCardDaoService @Inject constructor(
    private val flashCardDAO: FlashCardDAO,
    private val mapper: FlashCardEntityMapper
) {

    fun getAllFlashCards(): Flow<List<FlashCard>> {
        return flashCardDAO.getFlashCards().map {
            mapper.mapFromModelList(it)
        }
    }

    fun getFlashCardById(id: Int): Flow<FlashCard> {
        return flashCardDAO.getFlashCardById(id).map {
            mapper.mapFromModel(it)
        }
    }

    suspend fun insertFlashCard(data: FlashCard): Long {
        val entity = mapper.mapToModel(data)
        return flashCardDAO.insertFlashCard(entity)
    }

    suspend fun deleteFlashCard(data: FlashCard): Int {
        return flashCardDAO.deleteFlashCard(mapper.mapToModel(data))
    }

    suspend fun updateFlashCard(data: FlashCard): Int {
        return flashCardDAO.updateFlashCard(mapper.mapToModel(data))
    }

    fun getFlashCardsCount(): Flow<List<Int>> {
        val f = flow {

            val b1 = flashCardDAO.getFlashCardsCount(1)
            val b2 = flashCardDAO.getFlashCardsCount(2)
            val b3 = flashCardDAO.getFlashCardsCount(3)
            val b4 = flashCardDAO.getFlashCardsCount(4)
            val b5 = flashCardDAO.getFlashCardsCount(5)

            val c = combine(b1, b2, b3, b4, b5) { b1, b2, b3, b4, b5 -> listOf(b1, b2, b3, b4, b5) }

            emitAll(c)
        }

        return f
    }

    fun getFlashcardsByBox(boxNumber: Int): Flow<List<FlashCard>> =
        flashCardDAO.getFlashCardsByBox(boxNumber).map { mapper.mapFromModelList(it) }

    fun getToKnowCount(): Flow<Int> = flashCardDAO.getToKnowCount()
    fun getKnownCount(): Flow<Int> = flashCardDAO.getKnownCount()
    fun getLearnedCount(): Flow<Int> = flashCardDAO.getLearnedCount()
    fun getStudyFlashCards(): List<FlashCard> =
        mapper.mapFromModelList(flashCardDAO.getStudyCards())

    fun getDeletedFlashCards() = mapper.mapFromModelList(flashCardDAO.getDeletedFlashCards())
    fun deleteDeletedFlashcards() = flashCardDAO.deleteDeletedFlashCards()
    fun getUpdatedFlashCards() = mapper.mapFromModelList(flashCardDAO.getUpdatedFlashCards())
}