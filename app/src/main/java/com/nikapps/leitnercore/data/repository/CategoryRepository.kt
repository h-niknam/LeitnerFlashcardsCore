package com.nikapps.leitnerflashcardsmvp.data.repository

import com.nikapps.leitnerflashcardsmvp.data.model.Resource
import com.nikapps.leitnerflashcardsmvp.data.network.FirebaseCategoryService
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val service: FirebaseCategoryService
) {

    fun getCategories() = flow {
        emit(Resource.Loading())
        val data = service.getCategories()
        emit(Resource.Success(data))
    }

    suspend fun getCategoryWords(catId: String): List<FlashCard> {
        return service.getCategoryWords(catId)
    }

}