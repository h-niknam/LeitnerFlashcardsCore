package com.nikapps.leitnerflashcardsmvp.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_flash_card")
data class FlashCardEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var front: String,
    var back: String,
    var description: String?,
    var phonetics: String?,
    var thumbnail: String?,
    @ColumnInfo(name = "box_number")
    var boxNumber: Int = 1,
    @ColumnInfo(name = "last_date")
    var lastDate: String? = null,
    var status: String
)