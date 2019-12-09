package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.LeagueRepository
import io.github.mrizkifadil26.footballmatchschedule.view.LeagueInfoView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeagueInfoPresenter(
    val view: LeagueInfoView,
    val repository: LeagueRepository
) {
    fun getLeagueInfo(idLeague: Int) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getLeagueInfoFromRetrofit(idLeague)

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                data?.let {
                    view.showLeagueInfo(it[0])
                }
            }
        }
    }
}