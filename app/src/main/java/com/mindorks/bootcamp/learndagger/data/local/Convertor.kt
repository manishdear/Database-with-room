package com.mindorks.bootcamp.learndagger.data.local

import androidx.room.TypeConverter
import java.util.*

class Convertor {

    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let {
        Date(it)
    }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time
}