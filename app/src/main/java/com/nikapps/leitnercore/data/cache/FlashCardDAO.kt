package com.nikapps.leitnerflashcardsmvp.data.cache

import androidx.room.*
import com.nikapps.leitnerflashcardsmvp.data.cache.entity.FlashCardEntity
import com.nikapps.leitnercore.domain.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashCardDAO {

    @Query("select * from tbl_flash_card where ${QUERY_NOT_DELETED_WHERE_CLAUSE}")
    fun getFlashCards(): Flow<List<FlashCardEntity>>

    @Query("SELECT * FROM tbl_flash_card WHERE id = :id  LIMIT 1")
    fun getFlashCardById(id: Int): Flow<FlashCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashCard(flashCardEntity: FlashCardEntity): Long

    @Delete
    suspend fun deleteFlashCard(flashCardEntity: FlashCardEntity): Int

    @Update
    suspend fun updateFlashCard(flashCardEntity: FlashCardEntity): Int

    @Query("select count() from tbl_flash_card where box_number = :boxNumber and ${QUERY_NOT_DELETED_WHERE_CLAUSE}")
    fun getFlashCardsCount(boxNumber: Int): Flow<Int>

    @Query("select * from tbl_flash_card where box_number = :boxNumber and ${QUERY_NOT_DELETED_WHERE_CLAUSE}")
    fun getFlashCardsByBox(boxNumber: Int): Flow<List<FlashCardEntity>>

    @Query("select count() from tbl_flash_card where ${QUERY_STUDY_WHERE_CLAUSE} and ${QUERY_NOT_DELETED_WHERE_CLAUSE}")
    fun getToKnowCount(): Flow<Int>

    @Query("select * from tbl_flash_card where ${QUERY_STUDY_WHERE_CLAUSE} and ${QUERY_NOT_DELETED_WHERE_CLAUSE}")
    fun getStudyCards(): List<FlashCardEntity>

    @Query("select count() from tbl_flash_card where (julianday(date()) - julianday(date(last_date))) = 0 and box_number > 1")
    fun getKnownCount(): Flow<Int>

    @Query("select count() from tbl_flash_card where box_number = 6")
    fun getLearnedCount(): Flow<Int>

    @Query("select * from tbl_flash_card where status = 'delete'")
    fun getDeletedFlashCards(): List<FlashCardEntity>

    @Query("delete from tbl_flash_card where status = 'delete'")
    fun deleteDeletedFlashCards()

    @Query("select * from tbl_flash_card where status = 'update'")
    fun getUpdatedFlashCards(): List<FlashCardEntity>


    companion object {
        const val QUERY_STUDY_WHERE_CLAUSE: String = "((box_number = 1) or " +
                "(box_number = 2 and (julianday(date()) - julianday(date(last_date))) >= ${Constants.BOX2_PERIOD}  ) or " +
                "(box_number = 3 and (julianday(date()) - julianday(date(last_date))) >= ${Constants.BOX3_PERIOD} ) or " +
                "(box_number = 4 and (julianday(date()) - julianday(date(last_date))) >= ${Constants.BOX4_PERIOD} ) or " +
                "(box_number = 5 and (julianday(date()) - julianday(date(last_date))) >= ${Constants.BOX5_PERIOD} ))"

        const val QUERY_NOT_DELETED_WHERE_CLAUSE: String = "status != 'delete'"
    }
}