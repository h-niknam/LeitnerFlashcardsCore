package com.nikapps.leitnerflashcardsmvp.data.network.mapper

import com.nikapps.leitnerflashcardsmvp.data.network.dto.CategoryDTO
import com.nikapps.leitnerflashcardsmvp.domain.model.Category
import com.nikapps.leitnerflashcardsmvp.domain.model.CategoryLevel
import com.nikapps.leitnerflashcardsmvp.domain.model.EntityMapper
import javax.inject.Inject

class CategoryNetworkMapper @Inject constructor() : EntityMapper<CategoryDTO, Category> {

    override fun mapFromModel(data: CategoryDTO): Category {
        return Category(
            id = data.id,
            title =  data.title,
            thumb = data.thumbnail,
            level = CategoryLevel.valueOf(data.level)
        )
    }

    override fun mapToModel(data: Category): CategoryDTO {
        return CategoryDTO(
            id = data.id,
            title = data.title,
            thumbnail = data.thumb,
            level = data.level.name
        )
    }


    fun mapToModelList(data: List<Category>): List<CategoryDTO> {
        val out = ArrayList<CategoryDTO>()
        for (d in data) {
            out.add(mapToModel(d))
        }
        return out
    }

    fun mapFromModelList(data: List<CategoryDTO>): List<Category> {
        val out = ArrayList<Category>()
        for (d in data) {
            out.add(mapFromModel(d))
        }
        return out
    }


}