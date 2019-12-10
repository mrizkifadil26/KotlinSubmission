package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail

interface SearchMatchView {
    fun showSpinner()
    fun hideSpinner()
    fun showMatches(data: List<MatchDetail>?)
}