package com.nikapps.leitnerflashcardsmvp.usecase.common

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import javax.inject.Inject

class DeleteFlashCard @Inject constructor(
    private val repository: FlashCardRepository
) {

    suspend operator fun invoke(data: FlashCard) {
        repository.deleteFlashCard(data)
    }
}