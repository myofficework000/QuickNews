package com.example.newsapp.model.local

import androidx.room.TypeConverter

class Converter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromListString(value: List<String?>): String {
            var result = ""
            for (i in value.indices) {
                result += if (i != value.size) {
                    value[i] + ", "
                } else {
                    value[i]
                }
            }
            return result

        }

        @JvmStatic
        @TypeConverter
        fun toListString(value: String): List<String?> {
            val list = mutableListOf<String>()
            val newValue = value.split(",")
            for (i in newValue.indices) {
                list.add(newValue[i])
            }
            return list
        }
    }
}