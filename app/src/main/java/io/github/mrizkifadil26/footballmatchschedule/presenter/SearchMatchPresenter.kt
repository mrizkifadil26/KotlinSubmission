package io.github.mrizkifadil26.footballmatchschedule.presenter

import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.SearchMatchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMatchPresenter (
    val view: SearchMatchView,
    val repository: MatchRepository
) {
    fun getMatchByQuery(query: String) {
        view.showSpinner()

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getMatchByQueryFromRetrofit(query)
            val filteredData = data?.filter { item -> item.sport == "Soccer" }

            withContext(Dispatchers.Main) {
                view.hideSpinner()
                view.showMatches(filteredData)
            }
        }
    }
}