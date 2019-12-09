package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.LeagueInfo

interface LeagueInfoView {
    fun showSpinner()
    fun hideSpinner()
    fun showLeagueInfo(data: LeagueInfo)
}