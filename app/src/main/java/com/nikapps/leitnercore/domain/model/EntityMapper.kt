package com.nikapps.leitnerflashcardsmvp.domain.model

interface EntityMapper<Model, DomainModel> {
    fun mapFromModel(data: Model): DomainModel
    fun mapToModel(data: DomainModel): Model
}