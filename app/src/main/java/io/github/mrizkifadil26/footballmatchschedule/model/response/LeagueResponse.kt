package io.github.mrizkifadil26.footballmatchschedule.model.response

import com.google.gson.annotations.SerializedName
import io.github.mrizkifadil26.footballmatchschedule.model.data.League

data class LeagueResponse (
    @SerializedName("countrys")
    var countrys: List<League>
)