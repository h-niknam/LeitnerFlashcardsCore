package com.nikapps.leitnerflashcardsmvp.data.cache.mapper

import com.nikapps.leitnerflashcardsmvp.data.cache.entity.FlashCardEntity
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashcardBackupStatus
import com.nikapps.leitnerflashcardsmvp.domain.util.DateUtil
import com.nikapps.leitnerflashcardsmvp.domain.model.EntityMapper
import java.lang.Exception
import javax.inject.Inject

class FlashCardEntityMapper @Inject constructor(
    private val dateUtil: DateUtil
) : EntityMapper<FlashCardEntity, FlashCard> {

    override fun mapFromModel(data: FlashCardEntity): FlashCard {
        return FlashCard(
            id = data.id ?: 0,
            front = data.front,
            back = data.back,
            description = data.description,
            phonetics = data.phonetics,
            boxNumber = data.boxNumber,
            lastDate = dateUtil.convertStringToDate(data.lastDate ?: ""),
            thumbnail = data.thumbnail,
            status = try {
                FlashcardBackupStatus.valueOf(data.status)
            } catch (e: Exception) {
                FlashcardBackupStatus.nothing
            }
        )
    }

    override fun mapToModel(data: FlashCard): FlashCardEntity {
        return FlashCardEntity(
            id = if (data.id <= 0) null else data.id,
            front = data.front,
            back = data.back,
            description = data.description,
            phonetics = data.phonetics,
            boxNumber = data.boxNumber,
            lastDate = dateUtil.convertDateToString(data.lastDate),
            thumbnail = data.thumbnail,
            status = data.status.name
        )
    }

    fun mapToModelList(data: List<FlashCard>): List<FlashCardEntity> {
        val out = ArrayList<FlashCardEntity>()
        for (d in data) {
            out.add(mapToModel(d))
        }
        return out
    }

    fun mapFromModelList(data: List<FlashCardEntity>): List<FlashCard> {
        val out = ArrayList<FlashCard>()
        for (d in data) {
            out.add(mapFromModel(d))
        }
        return out
    }
}