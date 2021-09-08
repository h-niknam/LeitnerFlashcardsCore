package com.nikapps.leitnerflashcardsmvp.domain.model

data class Category(
    val id: String,
    var title: String,
    var thumb: String?,
    var level: CategoryLevel
)