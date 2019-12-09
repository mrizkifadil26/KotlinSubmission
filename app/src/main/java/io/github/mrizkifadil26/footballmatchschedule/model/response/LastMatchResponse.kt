package io.github.mrizkifadil26.footballmatchschedule.model.response

import com.google.gson.annotations.SerializedName
import io.github.mrizkifadil26.footballmatchschedule.model.data.LastMatch

data class LastMatchResponse (
    @SerializedName("events")
    val lastMatches: List<LastMatch>
)