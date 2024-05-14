package com.example.newsapp.utils

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

object Formatter {
    fun formatDate(dateString: String): String {
        if(dateString.isEmpty()){
            return ""
        }

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val parsedDate = parser.parse(dateString)

        val desiredFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val convertedDate = desiredFormat.format(parsedDate ?: "")
        return convertedDate
    }

}