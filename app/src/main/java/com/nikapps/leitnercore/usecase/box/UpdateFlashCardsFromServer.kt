package com.nikapps.leitnerflashcardsmvp.usecase.box

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import javax.inject.Inject

class UpdateFlashCardsFromServer @Inject constructor(
    private val repository: FlashCardRepository
) {
    operator suspend fun invoke() {
        repository.getFlashCardsFromServer()
    }
}