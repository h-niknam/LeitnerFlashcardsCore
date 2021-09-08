package com.nikapps.leitnerflashcardsmvp.domain.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateUtil constructor(
    private val dateFormat: SimpleDateFormat
) {

    fun convertFirebaseTimeStampToDate(input: Timestamp): Date {
        return input.toDate()
    }

    fun convertDateToFirebaseTimeStamp(input: Date): Timestamp {
        return Timestamp(input)
    }

    fun convertStringToDate(input: String): Date {
        return try{
            dateFormat.parse(input)
        }catch (e : Exception){
            return Date()
        }
    }

    fun convertDateToString(input: Date): String {
        return try{
            dateFormat.format(input)
        }catch (e : Exception){
            return ""
        }
    }
}