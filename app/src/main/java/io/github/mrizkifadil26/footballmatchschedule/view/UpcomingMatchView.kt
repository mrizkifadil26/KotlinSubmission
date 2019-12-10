package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.UpcomingMatch

interface UpcomingMatchView {
    fun showSpinner()
    fun hideSpinner()
    fun showUpcomingMatchList(data: List<UpcomingMatch>?)
}