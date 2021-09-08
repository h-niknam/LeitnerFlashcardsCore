package com.nikapps.leitnerflashcardsmvp.data.network

import com.nikapps.leitnerflashcardsmvp.data.network.FirebaseService.Companion.COLLECTION_FLASHCARDS
import com.nikapps.leitnerflashcardsmvp.data.network.dto.CategoryDTO
import com.nikapps.leitnerflashcardsmvp.data.network.dto.WordDTO
import com.nikapps.leitnerflashcardsmvp.data.network.mapper.CategoryNetworkMapper
import com.nikapps.leitnerflashcardsmvp.data.network.mapper.WordMapper
import com.nikapps.leitnerflashcardsmvp.domain.model.Category
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseCategoryService @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val wordMapper: WordMapper,
    private val mapper: CategoryNetworkMapper
) {

    suspend fun getCategories(): List<Category> {

        return mapper.mapFromModelList(
            firebaseFirestore
                .collection(COLLECTION_CATEGORIES)
                .get()
                .addOnFailureListener {
                }
                .addOnSuccessListener {

                }
                .await()
                .toObjects(CategoryDTO::class.java)
        )
    }

    suspend fun getCategoryWords(categoryId: String): List<FlashCard> {
        return wordMapper.mapFromModelList(
            firebaseFirestore
                .collection(COLLECTION_CATEGORIES)
                .document(categoryId)
                .collection(COLLECTION_FLASHCARDS)
                .get()
                .addOnFailureListener {

                }
                .addOnSuccessListener {

                }
                .await()
                .toObjects(WordDTO::class.java)
        )
    }

    companion object {
        const val COLLECTION_CATEGORIES = "categories"
    }
}