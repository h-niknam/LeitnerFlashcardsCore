package com.nikapps.leitnerflashcardsmvp.usecase.category

import com.nikapps.leitnerflashcardsmvp.data.repository.CategoryRepository
import com.nikapps.leitnerflashcardsmvp.data.model.Resource
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryWords @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(catId: String): Flow<Resource<List<FlashCard>>> {

        return flow {
            emit(Resource.Loading())
            val data = repository.getCategoryWords(catId)
            emit(Resource.Success(data))
        }
    }
}