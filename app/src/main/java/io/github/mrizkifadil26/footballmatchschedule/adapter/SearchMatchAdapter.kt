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
        private val searchAwayTeam= view.findViewById<TextView>(R.id.search_away_team)

        fun bindItem(match: MatchDetail) {

            searchSport.text = match.sport
            searchLeague.text = match.league
            if (match.round == 0) {
                searchRound.text = ""
            } else {
                searchRound.text = String.format(context.getString(R.string.match), match.round)
            }

            searchDate.text = dateFormatter(match.matchDate, "dd/MM/yyyy")
            searchTime.text = match.time

            if (match.homeScore == null && match.awayScore == null) {
                searchMatchStrip.visibility = View.GONE
                searchHomeScore.text = ""
                searchAwayScore.text = ""
            } else {
                searchMatchStrip.visibility = View.VISIBLE
                searchHomeScore.text = match.homeScore.toString()
                searchAwayScore.text = match.awayScore.toString()
            }

            searchHomeTeam.text = match.homeTeam
            searchAwayTeam.text = match.awayTeam

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