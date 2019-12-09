package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.League

interface LeagueView {
    fun showSpinner()
    fun hideSpinner()
    fun showLeagueList(data: List<League>)
}