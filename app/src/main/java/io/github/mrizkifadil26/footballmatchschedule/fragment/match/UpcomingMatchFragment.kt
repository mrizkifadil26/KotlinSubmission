package io.github.mrizkifadil26.footballmatchschedule.fragment.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.adapter.UpcomingMatchAdapter
import io.github.mrizkifadil26.footballmatchschedule.model.data.UpcomingMatch
import io.github.mrizkifadil26.footballmatchschedule.presenter.UpcomingMatchPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.UpcomingMatchView
import kotlinx.android.synthetic.main.fragment_upcoming_match.*

class UpcomingMatchFragment(val idLeague: Int) : Fragment(), UpcomingMatchView {

    val repository: MatchRepository = MatchRepository()

    var matches: MutableList<UpcomingMatch> = mutableListOf()
    lateinit var presenter: UpcomingMatchPresenter
    lateinit var adapter: UpcomingMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_upcoming_match.apply {
            layoutManager = LinearLayoutManager(context)
        }

        adapter = UpcomingMatchAdapter(view.context, matches)
        presenter = UpcomingMatchPresenter(this, repository)

        presenter.getUpcomingMatch(idLeague)
    }

    override fun showSpinner() {
        progress_upcoming_match.visibility = View.VISIBLE
        recycler_upcoming_match.visibility = View.GONE
    }

    override fun hideSpinner() {
        progress_upcoming_match.visibility = View.GONE
        recycler_upcoming_match.visibility = View.VISIBLE
    }

    override fun showUpcomingMatchList(data: List<UpcomingMatch>?) {
        if (data != null) {
            recycler_upcoming_match.adapter = adapter
            matches.clear()
            matches.addAll(data)
            adapter.notifyDataSetChanged()
        } else {
            recycler_upcoming_match.visibility = View.GONE
            upcoming_match_status.visibility = View.VISIBLE
            upcoming_match_status.text = getString(R.string.no_upcoming_match)
        }
    }

}
