package com.nikapps.leitnerflashcardsmvp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FlashCard(
    var id: Int = 0,
    var front: String,
    var back: String,
    var description: String?,
    var phonetics: String? = null,
    var thumbnail: String?,
    var boxNumber: Int = 1,
    var lastDate: Date = Date(),
    var status: FlashcardBackupStatus = FlashcardBackupStatus.nothing

) : Parcelable