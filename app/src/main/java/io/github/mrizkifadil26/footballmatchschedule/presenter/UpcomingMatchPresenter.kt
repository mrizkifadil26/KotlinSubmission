package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.UpcomingMatchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpcomingMatchPresenter(
    val view: UpcomingMatchView,
    val repository: MatchRepository
) {
    fun getUpcomingMatch(idLeague: Int) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getUpcomingMatchFromRetrofit(idLeague)

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                if (data != null) {
                    view.showUpcomingMatchList(data)
                } else {
                    view.showStatus()
                }

            }
        }
    }
}