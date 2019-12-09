package io.github.mrizkifadil26.footballmatchschedule.view

import io.github.mrizkifadil26.footballmatchschedule.model.data.LastMatch

interface LastMatchView {
    fun showSpinner()
    fun hideSpinner()
    fun showLastMatchList(data: List<LastMatch>)
}