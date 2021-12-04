package com.example.cashenger.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentDateTime() : String {
        val calendar = Calendar.getInstance()
        val currentDateTime = calendar.time
        return SimpleDateFormat("HH:mm dd//MM/yy", Locale.getDefault()).format(currentDateTime)
    }

}