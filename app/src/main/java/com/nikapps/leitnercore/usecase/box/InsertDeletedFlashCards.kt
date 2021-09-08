package com.nikapps.leitnerflashcardsmvp.usecase.box

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import javax.inject.Inject

class InsertDeletedFlashCards @Inject constructor(
    private val repository: FlashCardRepository
) {

    suspend operator fun invoke() {
        repository.insertDeletedFlashCards()
    }
}