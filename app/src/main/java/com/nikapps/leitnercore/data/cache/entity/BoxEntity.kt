package com.nikapps.leitnerflashcardsmvp.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_box")
data class BoxEntity(
    @PrimaryKey
    var id: Int,
    var title: String?,
    var imagePath: String?
)