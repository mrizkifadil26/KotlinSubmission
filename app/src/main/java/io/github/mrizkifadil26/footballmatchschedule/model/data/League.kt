package io.github.mrizkifadil26.footballmatchschedule.model.data

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    val id: Int,
    @SerializedName("strLeague")
    val title: String?,
    @SerializedName("strLogo")
    val logo: String?
)