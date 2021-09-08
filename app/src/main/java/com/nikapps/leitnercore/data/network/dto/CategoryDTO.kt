package com.nikapps.leitnerflashcardsmvp.data.network.dto

data class CategoryDTO(
    var id: String,
    var title: String,
    var thumbnail: String?,
    var level: String
) {
    constructor() : this(
        "",
        "",
        "",
        ""
    )
}