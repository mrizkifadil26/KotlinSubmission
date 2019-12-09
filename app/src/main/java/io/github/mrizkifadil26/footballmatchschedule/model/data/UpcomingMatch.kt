package io.github.mrizkifadil26.footballmatchschedule.model.data

import com.google.gson.annotations.SerializedName

data class UpcomingMatch (
    @SerializedName("idEvent")
    val id: Int,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("strAwayTeam")
    val awayTeam: String,
    @SerializedName("intRound")
    val round: Int,
    @SerializedName("strDate")
    val date: String,
    @SerializedName("strTime")
    val time: String
)