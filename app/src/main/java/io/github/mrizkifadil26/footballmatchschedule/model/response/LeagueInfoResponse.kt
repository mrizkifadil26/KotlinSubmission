package io.github.mrizkifadil26.footballmatchschedule.model.response

import com.google.gson.annotations.SerializedName
import io.github.mrizkifadil26.footballmatchschedule.model.data.LeagueInfo

data class LeagueInfoResponse(
    @SerializedName("leagues")
    var league: List<LeagueInfo>
)