package io.github.mrizkifadil26.footballmatchschedule.model.data

import com.google.gson.annotations.SerializedName

data class LeagueInfo(
    @SerializedName("idLeague")
    val id: Int,
    @SerializedName("strLeague")
    val leagueName: String?,
    @SerializedName("strDivision")
    val division: Int?,
    @SerializedName("idCup")
    val idCup: Int?,
    @SerializedName("strCountry")
    val countryName: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strLogo")
    val logo: String?,
    @SerializedName("strBanner")
    val banner: String?,
    @SerializedName("intFormedYear")
    val formedYear: Int?,
    @SerializedName("dateFirstEvent")
    val firstEvent: String?,
    @SerializedName("strWebsite")
    val website: String?,
    @SerializedName("strFacebook")
    val facebook: String?,
    @SerializedName("strTwitter")
    val twitter: String?,
    @SerializedName("strYoutube")
    val youtube: String?
)