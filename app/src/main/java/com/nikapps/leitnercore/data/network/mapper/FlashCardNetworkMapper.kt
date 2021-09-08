package com.nikapps.leitnerflashcardsmvp.data.network.mapper

import com.nikapps.leitnerflashcardsmvp.data.network.dto.FlashCardDTO
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.nikapps.leitnerflashcardsmvp.domain.util.DateUtil
import com.nikapps.leitnerflashcardsmvp.domain.model.EntityMapper
import java.lang.Exception
import javax.inject.Inject

class FlashCardNetworkMapper @Inject constructor(
    private val dateUtil: DateUtil
) : EntityMapper<FlashCardDTO, FlashCard> {
    override fun mapFromModel(data: FlashCardDTO): FlashCard {
        return FlashCard(
            id = try {
                data.id.toInt()
            } catch (e: Exception) {
                0
            },
            front = data.front,
            back = data.back,
            description = data.description,
            phonetics = data.phonetics,
            boxNumber = data.boxNumber,
            lastDate = dateUtil.convertFirebaseTimeStampToDate(data.lastDate),
            thumbnail = data.thumb
        )
    }

    override fun mapToModel(data: FlashCard): FlashCardDTO {
        return FlashCardDTO(
            id = data.id.toString(),
            front = data.front,
            back = data.back,
            description = data.description,
            phonetics = data.phonetics,
            boxNumber = data.boxNumber,
            lastDate = dateUtil.convertDateToFirebaseTimeStamp(data.lastDate),
            thumb = data.thumbnail
        )
    }

    fun mapToModelList(data: List<FlashCard>): List<FlashCardDTO> {
        val out = ArrayList<FlashCardDTO>()
        for (d in data) {
            out.add(mapToModel(d))
        }
        return out
    }

    fun mapFromModelList(data: List<FlashCardDTO>): List<FlashCard> {
        val out = ArrayList<FlashCard>()
        for (d in data) {
            out.add(mapFromModel(d))
        }
        return out
    }
}