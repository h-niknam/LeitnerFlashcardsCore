package com.nikapps.leitnerflashcardsmvp.usecase.study

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import javax.inject.Inject

class GetStudyFlashCards @Inject constructor(
    private val repository: FlashCardRepository
) {
    operator fun invoke() = repository.getStudyFlashCards()
}