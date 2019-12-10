package io.github.mrizkifadil26.footballmatchschedule.model.response

import com.google.gson.annotations.SerializedName
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail

data class MatchQueryResponse (
    @SerializedName("event")
    val matches: List<MatchDetail>?
)