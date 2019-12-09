package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.LeagueRepository
import io.github.mrizkifadil26.footballmatchschedule.view.LeagueView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaguePresenter(
    val view: LeagueView,
    val repository: LeagueRepository
) {

    fun getLeagueListData(countryName: String) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.getLeagueFromRetrofit(countryName, "Soccer")

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                list?.let {
                    view.showLeagueList(it)
                }
            }
        }
    }

}