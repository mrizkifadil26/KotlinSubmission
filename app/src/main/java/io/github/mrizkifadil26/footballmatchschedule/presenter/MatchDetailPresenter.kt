package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.MatchDetailView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchDetailPresenter(
    val view: MatchDetailView,
    val repository: MatchRepository
) {
    fun getMatchDetail(idMatch: Int) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getMatchDetailFromRetrofit(idMatch)

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                data?.let {
                    view.showMatchDetail(it[0])
                }
            }
        }
    }
}