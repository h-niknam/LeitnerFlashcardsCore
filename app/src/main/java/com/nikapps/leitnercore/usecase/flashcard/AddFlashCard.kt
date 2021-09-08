package com.nikapps.leitnerflashcardsmvp.usecase.flashcard

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import javax.inject.Inject

class AddFlashCard @Inject constructor(
    private val repository: FlashCardRepository
) {
    operator suspend fun invoke(data: FlashCard) {
        repository.insertFlashCard(data)
    }
}