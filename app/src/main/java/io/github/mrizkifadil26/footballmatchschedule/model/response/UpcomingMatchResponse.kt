package io.github.mrizkifadil26.footballmatchschedule.model.response

import com.google.gson.annotations.SerializedName
import io.github.mrizkifadil26.footballmatchschedule.model.data.UpcomingMatch

class UpcomingMatchResponse (
    @SerializedName("events")
    val upcomingMatches: List<UpcomingMatch>?
)