package io.github.mrizkifadil26.footballmatchschedule.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.adapter.SearchMatchAdapter
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail
import io.github.mrizkifadil26.footballmatchschedule.presenter.SearchMatchPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.SearchMatchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchMatchView {

    val repository: MatchRepository = MatchRepository()

    var matches: MutableList<MatchDetail> = mutableListOf()
    lateinit var presenter: SearchMatchPresenter
    lateinit var adapter: SearchMatchAdapter

    companion object {
        const val EXTRA_SEARCH = "extra_search"
        const val EXTRA_SEARCH_MATCH = "extra_search_match"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.title = ""

        val query = intent?.getStringExtra(EXTRA_SEARCH)

        recycler_search.apply {
            layoutManager = LinearLayoutManager(context)
        }

        adapter = SearchMatchAdapter(this, matches)
        presenter = SearchMatchPresenter(this, repository)

        if (intent?.action != null) {
            if (intent?.action == EXTRA_SEARCH_MATCH) {
                if (query != null) {
                    presenter.getMatchByQuery(query)
                }
            }
        }

        search_bar.apply {
            setQuery(query, false)
            clearFocus()
        }

        val inputMethod = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(search_bar.windowToken, 0)

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    presenter.getMatchByQuery(query)
                }

                search_bar.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }

        search_bar.setOnQueryTextListener(queryTextListener)
    }


    override fun showSpinner() {
        progress_search.visibility = View.VISIBLE
        recycler_search.visibility = View.GONE
    }

    override fun hideSpinner() {
        progress_search.visibility = View.GONE
        recycler_search.visibility = View.VISIBLE
    }

    override fun showMatches(data: List<MatchDetail>) {
        recycler_search.adapter = adapter
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
