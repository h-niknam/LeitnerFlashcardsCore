package com.nikapps.leitnerflashcardsmvp.data.network

import com.nikapps.leitnerflashcardsmvp.data.network.dto.FlashCardDTO
import com.nikapps.leitnerflashcardsmvp.data.network.mapper.FlashCardNetworkMapper
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val mapper: FlashCardNetworkMapper
) {

    suspend fun insertFlashCard(flashCard: FlashCard): Boolean {
        var success = false
        val dto = mapper.mapToModel(flashCard)
        firebaseFirestore
            .collection(COLLECTION_FLASHCARDS)
            .document(USER_ID)
            .collection(COLLECTION_FLASHCARDS)
            .document(flashCard.id.toString())
            .set(dto)
            .addOnFailureListener {
            }
            .addOnSuccessListener {
                success = true
            }
            .await()

        return success
    }

    suspend fun insertFlashCards(flashcards: List<FlashCard>): Boolean {
        var success = false
        val dtos = mapper.mapToModelList(flashcards)

        firebaseFirestore.runBatch {
            dtos.forEach {
                firebaseFirestore
                    .collection(COLLECTION_FLASHCARDS)
                    .document(USER_ID)
                    .collection(COLLECTION_FLASHCARDS)
                    .document(it.id.toString())
                    .set(it)
            }
        }.addOnFailureListener {}
            .addOnSuccessListener {
                success = true
            }
            .await()

        return success
    }

    suspend fun deleteFlashCard(flashCard: FlashCard): Boolean {

        var success = false
        val dto = mapper.mapToModel(flashCard)

        firebaseFirestore.runTransaction {

            firebaseFirestore.collection(COLLECTION_FLASHCARDS)
                .document(USER_ID)
                .collection(COLLECTION_FLASHCARDS)
                .document(dto.id)
                .delete()
                .addOnFailureListener {
                    success = false
                }
                .addOnSuccessListener {
                    success = true
                }

            firebaseFirestore.collection(COLLECTION_FLASHCARDS)
                .document(USER_ID)
                .collection(COLLECTION_DELETED)
                .document(flashCard.id.toString())
                .set(dto)
                .addOnSuccessListener {
                    success = true
                }
                .addOnFailureListener {
                    success = false
                }

        }.await()



        return success

    }

    suspend fun insertDeletedFlasgCards(flashCards: List<FlashCard>): Boolean {
        var result = false
        firebaseFirestore.runBatch {
            val ref = firebaseFirestore
                .collection(COLLECTION_FLASHCARDS)
                .document(USER_ID)
                .collection(COLLECTION_DELETED)

            for (f in flashCards) {
                val documentRef = ref.document(f.id.toString())
                it.set(documentRef, mapper.mapToModel(f))
            }
        }.addOnSuccessListener {
            result = true
        }.await()

        return result
    }


    suspend fun getFlashCards(): List<FlashCard> {

        return mapper.mapFromModelList(
            firebaseFirestore
                .collection(COLLECTION_FLASHCARDS)
                .document(USER_ID)
                .collection(COLLECTION_FLASHCARDS)
                .get()
                .addOnFailureListener {
                }
                .addOnSuccessListener {
                }
                .await()
                .toObjects(FlashCardDTO::class.java)
        )
    }

    suspend fun getDeletedFlashCards(): List<FlashCard> {

        return mapper.mapFromModelList(
            firebaseFirestore
                .collection(COLLECTION_FLASHCARDS)
                .document(USER_ID)
                .collection(COLLECTION_DELETED)
                .get()
                .await()
                .toObjects(FlashCardDTO::class.java)
        )
    }


    companion object {
        const val COLLECTION_FLASHCARDS = "flashcards"
        const val COLLECTION_DELETED = "deleted"
        const val USER_ID = "MPN37QNWcjYbb8cLOEOoS2j5GVr1"
    }
}