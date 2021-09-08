package com.nikapps.leitnerflashcardsmvp.data.network.dto

data class WordDTO(
    var front: String,
    var back: String,
    var description: String,
    var phonetics: String,
    var thumb: String
) {

    constructor() : this(
        "",
        "",
        "",
        "",
        "",
    )
}