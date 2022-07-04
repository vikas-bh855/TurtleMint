package com.turtlemint.issues

import java.text.SimpleDateFormat

fun String.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-dd-mm'T'HH:mm:ss")
    val date = simpleDateFormat.parse(this)
    val formatted = SimpleDateFormat("mm-dd-yyyy")
    return formatted.format(date)
}