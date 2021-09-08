package com.nikapps.leitnerflashcardsmvp.data.network.mapper

import com.nikapps.leitnerflashcardsmvp.data.network.dto.WordDTO
import com.nikapps.leitnerflashcardsmvp.domain.model.FlashCard
import com.nikapps.leitnerflashcardsmvp.domain.model.EntityMapper
import javax.inject.Inject
import kotlin.collections.ArrayList

class WordMapper @Inject constructor() : EntityMapper<WordDTO, FlashCard> {

    override fun mapFromModel(data: WordDTO): FlashCard {
        return FlashCard(
            front = data.front,
            back = data.back,
            description = data.description,
            phonetics = data.phonetics,
            thumbnail = data.thumb
        )
    }

    override fun mapToModel(data: FlashCard): WordDTO {
        return WordDTO(
            front = data.front,
            back = data.back,
            description = data.description ?: "",
            phonetics = data.phonetics ?: "",
            thumb = data.thumbnail ?: ""
        )
    }

    fun mapFromModelList(data: List<WordDTO>) : List<FlashCard>{
        val list = ArrayList<FlashCard>()
        for (d in data){
            list.add(mapFromModel(d))
        }
        return list
    }

    fun mapToModelList(data: List<FlashCard>) : List<WordDTO>{
        val list = ArrayList<WordDTO>()
        for (d in data){
            list.add(mapToModel(d))
        }
        return list
    }
}