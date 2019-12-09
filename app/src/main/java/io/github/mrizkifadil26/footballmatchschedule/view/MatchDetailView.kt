package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail

interface MatchDetailView {
    fun showSpinner()
    fun hideSpinner()
    fun showMatchDetail(data: MatchDetail)
}