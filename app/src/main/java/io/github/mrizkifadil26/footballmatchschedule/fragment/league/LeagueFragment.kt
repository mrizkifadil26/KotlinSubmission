package io.github.mrizkifadil26.footballmatchschedule.fragment.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.adapter.LeagueAdapter
import io.github.mrizkifadil26.footballmatchschedule.model.data.League
import io.github.mrizkifadil26.footballmatchschedule.presenter.LeaguePresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.LeagueRepository
import io.github.mrizkifadil26.footballmatchschedule.view.LeagueView
import kotlinx.android.synthetic.main.fragment_league.*

class LeagueFragment : Fragment(), LeagueView {

    val repository: LeagueRepository = LeagueRepository()

    private var leagues: MutableList<League> = mutableListOf()
    lateinit var presenter: LeaguePresenter
    lateinit var adapter: LeagueAdapter

    private lateinit var countryName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_league.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        adapter = LeagueAdapter(view.context, leagues)
        presenter = LeaguePresenter(this, repository)

        val spinnerItems = resources.getStringArray(R.array.country_name)
        val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                countryName = spinner.selectedItem.toString()
                presenter.getLeagueListData(countryName)
            }

        }
    }

    override fun showSpinner() {
        progressBar.visibility = View.VISIBLE
        recycler_league.visibility = View.GONE
    }

    override fun hideSpinner() {
        progressBar.visibility = View.GONE
        recycler_league.visibility = View.VISIBLE
    }

    override fun showLeagueList(data: List<League>) {
        recycler_league.adapter = adapter
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
