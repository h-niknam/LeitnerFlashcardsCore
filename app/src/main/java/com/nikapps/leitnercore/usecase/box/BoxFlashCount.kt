package com.nikapps.leitnerflashcardsmvp.usecase.box

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BoxFlashCount @Inject constructor(
    private val repository: FlashCardRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        return repository.getBoxFlashCardsCount()
    }
}