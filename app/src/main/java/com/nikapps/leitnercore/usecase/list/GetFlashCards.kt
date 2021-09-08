package com.nikapps.leitnerflashcardsmvp.usecase.list

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import com.nikapps.leitnerflashcardsmvp.data.model.Resource
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlashCards @Inject constructor(
    private val repository: FlashCardRepository
) {

    operator fun invoke(boxNumber: Int): Flow<Resource<List<FlashCard>>> {
        return repository.getFlashCards(boxNumber, true)
    }

}