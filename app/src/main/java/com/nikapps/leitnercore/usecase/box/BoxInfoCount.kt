package com.nikapps.leitnerflashcardsmvp.usecase.box

import com.nikapps.leitnerflashcardsmvp.data.repository.FlashCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BoxInfoCount @Inject constructor(
    private val repository: FlashCardRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        val f = flow {

            val toKnow = repository.getToKnowCount()
            val known = repository.getKnownCount()
            val learned = repository.getLearnedCount()
            val c = combine(toKnow,known,learned){
                    toKnow,known,learned -> listOf(toKnow, known, learned)
            }
            emitAll(c)
        }

        return f
    }
}
