package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.LastMatchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LastMatchPresenter(
    val view: LastMatchView,
    val repository: MatchRepository
) {
    fun getLastMatch(idLeague: Int) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getLastMatchFromRetrofit(idLeague)

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                if (data != null) {
                    view.showLastMatchList(data)
                } else {
                    view.showLastMatchStatus()
                }
            }
        }
    }
}