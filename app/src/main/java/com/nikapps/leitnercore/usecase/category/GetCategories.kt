package com.nikapps.leitnerflashcardsmvp.usecase.category

import com.nikapps.leitnerflashcardsmvp.data.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: CategoryRepository
) {

    operator fun invoke() = repository.getCategories()
}