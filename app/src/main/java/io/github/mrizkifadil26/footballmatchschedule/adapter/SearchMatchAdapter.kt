package io.github.mrizkifadil26.footballmatchschedule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.activity.MatchDetailActivity
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail
import io.github.mrizkifadil26.footballmatchschedule.util.Config.dateFormatter

class SearchMatchAdapter (
    val context: Context,
    private val matches: List<MatchDetail>
) : RecyclerView.Adapter<SearchMatchAdapter.SearchMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchViewHolder {
        return SearchMatchViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: SearchMatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }

    inner class SearchMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val parentLayout = view.findViewById<CardView>(R.id.search_layout)
        private val searchSport = view.findViewById<TextView>(R.id.search_sport)
        private val searchLeague = view.findViewById<TextView>(R.id.search_league)
        private val searchRound = view.findViewById<TextView>(R.id.search_round)
        private val searchDate = view.findViewById<TextView>(R.id.search_date)
        private val searchTime = view.findViewById<TextView>(R.id.search_time)
        private val searchHomeScore = view.findViewById<TextView>(R.id.search_home_score)
        private val searchMatchStrip = view.findViewById<TextView>(R.id.search_strip)
        private val searchAwayScore = view.findViewById<TextView>(R.id.search_away_score)
        private val searchHomeTeam = view.findViewById<TextView>(R.id.search_home_team)
        private val searchVersus = view.findViewById<TextView>(R.id.search_versus)
        private val searchAwayTeam= view.findViewById<TextView>(R.id.search_away_team)

        fun bindItem(match: MatchDetail) {

            if (match.sport != null) {
                searchSport.text = match.sport
            } else {
                searchSport.text = ""
            }

            if (match.league != null) {
                searchLeague.text = match.league
            } else {
                searchLeague.text = match.league
            }

            if (match.round != null) {
                searchRound.text = String.format(context.getString(R.string.match), match.round)
            } else {
                searchRound.text = ""
            }

            if (match.date != null) {
                searchDate.text = dateFormatter(match.date, "dd/MM/yyyy")
            } else {
                searchDate.text = ""
            }

            if (match.time != null) {
                searchTime.text = match.time
            } else {
                searchTime.text = ""
            }

            if (match.homeScore != null && match.awayScore != null) {
                searchMatchStrip.visibility = View.VISIBLE
                searchHomeScore.text = match.homeScore.toString()
                searchAwayScore.text = match.awayScore.toString()
            } else {
                searchMatchStrip.visibility = View.GONE
                searchHomeScore.text = ""
                searchAwayScore.text = ""
            }

            if (match.homeTeam != null && match.awayTeam != null) {
                searchVersus.visibility = View.VISIBLE
                searchHomeTeam.text = match.homeTeam
                searchAwayTeam.text = match.awayTeam
            } else {
                searchVersus.visibility = View.GONE
                searchHomeTeam.text = ""
                searchAwayTeam.text = ""
            }

            parentLayout.setOnClickListener {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.apply {
                    putExtra(MatchDetailActivity.EXTRA_MATCH_ID, match.id)
                }
                context.startActivity(intent)
            }
        }
    }
}