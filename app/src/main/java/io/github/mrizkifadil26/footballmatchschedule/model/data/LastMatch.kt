package io.github.mrizkifadil26.footballmatchschedule.model.data

import com.google.gson.annotations.SerializedName

data class LastMatch (
    @SerializedName("idEvent")
    val id: Int,
    @SerializedName("intRound")
    val round: Int?,
    @SerializedName("dateEvent")
    val date: String?,
    @SerializedName("strTime")
    val time: String?,
    @SerializedName("strHomeTeam")
    val homeTeam: String?,
    @SerializedName("strAwayTeam")
    val awayTeam: String?,
    @SerializedName("intHomeScore")
    val homeScore: Int?,
    @SerializedName("intAwayScore")
    val awayScore: Int?,
    @SerializedName("strHomeGoalDetails")
    val homeGoals: String?,
    @SerializedName("strAwayGoalDetails")
    val awayGoals: String?
)