package com.nikapps.leitnerflashcardsmvp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Box(
    var id: Int,
    var title: String?,
    var imagePath: String?
) : Parcelable