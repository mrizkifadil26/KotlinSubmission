package io.github.mrizkifadil26.footballmatchschedule.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Config {
    const val API_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun dateFormatter(date: String, pattern: String): String? {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        val parseData: Date?
        var result: String? = ""
        try {
            parseData = parser.parse(date)
            result = parseData?.let {
                formatter.format(it)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }
}