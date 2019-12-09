package io.github.mrizkifadil26.footballmatchschedule.model.data

import com.google.gson.annotations.SerializedName

data class MatchDetail(
    //    Basic Info
    @SerializedName("idEvent")
    val id: Int,
    @SerializedName("strSport")
    val sport: String,
    @SerializedName("strLeague")
    val league: String,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("strAwayTeam")
    val awayTeam: String,
    @SerializedName("intRound")
    val round: Int,
    @SerializedName("dateEvent")
    val matchDate: String,
    @SerializedName("strDate")
    val date: String,
    @SerializedName("strTime")
    val time: String,

    //    Match Info
    @SerializedName("intHomeScore")
    val homeScore: Int?,
    @SerializedName("intAwayScore")
    val awayScore: Int?,
    @SerializedName("strHomeGoalDetails")
    val homeGoals: String?,
    @SerializedName("strAwayGoalDetails")
    val awayGoals: String?,
    @SerializedName("strHomeYellowCards")
    val homeYellowCards: String?,
    @SerializedName("strAwayYellowCards")
    val awayYellowCards: String?,
    @SerializedName("strHomeRedCards")
    val homeRedCards: String?,
    @SerializedName("strAwayRedCards")
    val awayRedCards: String?,

    //    Squad Info
    //    Home
    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalkeeper: String?,
    @SerializedName("strHomeLineupDefense")
    var homeDefender: String?,
    @SerializedName("strHomeLineupMidfield")
    var homeMidfielder: String?,
    @SerializedName("strHomeLineupForward")
    var homeForward: String?,
    @SerializedName("strHomeLineupSubstitutes")
    var homeSubstitutes: String?,
    //    Away
    @SerializedName("strAwayLineupGoalkeeper")
    var awayGoalkeeper: String?,
    @SerializedName("strAwayLineupDefense")
    var awayDefender: String?,
    @SerializedName("strAwayLineupMidfield")
    var awayMidfielder: String?,
    @SerializedName("strAwayLineupForward")
    var awayForward: String?,
    @SerializedName("strAwayLineupSubstitutes")
    var awaySubstitutes: String?
)