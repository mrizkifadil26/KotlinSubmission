package io.github.mrizkifadil26.footballmatchschedule.fragment.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.adapter.LastMatchAdapter
import io.github.mrizkifadil26.footballmatchschedule.model.data.LastMatch
import io.github.mrizkifadil26.footballmatchschedule.presenter.LastMatchPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.view.LastMatchView
import kotlinx.android.synthetic.main.fragment_last_match.*

class LastMatchFragment(
    val idLeague: Int
) : Fragment(), LastMatchView {

    val repository: MatchRepository = MatchRepository()

    var matches: MutableList<LastMatch> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    lateinit var adapter: LastMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_last_match.apply {
            layoutManager = LinearLayoutManager(context)
        }

        adapter = LastMatchAdapter(view.context, matches)
        presenter = LastMatchPresenter(this, repository)

        presenter.getLastMatch(idLeague)
    }

    override fun showSpinner() {
        progress_last_match.visibility = View.VISIBLE
        recycler_last_match.visibility = View.GONE
    }

    override fun hideSpinner() {
        progress_last_match.visibility = View.GONE
        recycler_last_match.visibility = View.VISIBLE
    }

    override fun showLastMatchList(data: List<LastMatch>) {
        recycler_last_match.adapter = adapter
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLastMatchStatus() {
        last_match_status.visibility = View.VISIBLE
        last_match_status.text = getString(R.string.no_recent_match)
    }

}
