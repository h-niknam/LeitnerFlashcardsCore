package com.nikapps.leitnerflashcardsmvp.data.network.dto

import com.google.firebase.Timestamp

data class FlashCardDTO(
    var id: String,
    var front: String,
    var back: String,
    var description: String?,
    var thumb: String?,
    var phonetics: String?,
    var boxNumber: Int,
    var lastDate: Timestamp,
){

    // no arg constructor for firestore
    constructor(): this(
        "0",
        "",
        "",
        "",
        "",
        "",
        0,
        Timestamp.now()
    )
}