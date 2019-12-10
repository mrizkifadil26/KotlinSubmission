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
import io.github.mrizkifadil26.footballmatchschedule.db.FavoriteMatchDatabase
import io.github.mrizkifadil26.footballmatchschedule.util.Config.dateFormatter

class FavoriteMatchAdapter(
    val context: Context,
    private val matches: List<FavoriteMatchDatabase>
) : RecyclerView.Adapter<FavoriteMatchAdapter.FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_favorite_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }

    inner class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val parentLayout = view.findViewById<CardView>(R.id.favorite_match_layout)
        private val favoriteMatchSport = view.findViewById<TextView>(R.id.favorite_match_sport)
        private val favoriteMatchLeague = view.findViewById<TextView>(R.id.favorite_match_league)
        private val favoriteMatchRound = view.findViewById<TextView>(R.id.favorite_match_round)
        private val favoriteMatchDate = view.findViewById<TextView>(R.id.favorite_match_date)
        private val favoriteMatchTime = view.findViewById<TextView>(R.id.favorite_match_time)
        private val favoriteMatchHomeScore = view.findViewById<TextView>(R.id.favorite_match_home_score)
        private val favoriteMatchMatchStrip = view.findViewById<TextView>(R.id.favorite_match_strip)
        private val favoriteMatchAwayScore = view.findViewById<TextView>(R.id.favorite_match_away_score)
        private val favoriteMatchHomeTeam = view.findViewById<TextView>(R.id.favorite_match_home_team)
        private val favoriteMatchAwayTeam = view.findViewById<TextView>(R.id.favorite_match_away_team)

        fun bindItem(favorite: FavoriteMatchDatabase) {

            favoriteMatchSport.text = favorite.matchSport
            favoriteMatchLeague.text = favorite.matchLeague
            if (favorite.matchRound == 0) {
                favoriteMatchRound.text = ""
            } else {
                favoriteMatchRound.text = String.format(context.getString(R.string.match), favorite.matchRound)
            }

            favoriteMatchDate.text = favorite.matchDate?.let {
                dateFormatter(it, "dd/MM/yyyy")
            }

            favoriteMatchTime.text = favorite.matchTime

            if (favorite.matchHomeScore == null && favorite.matchAwayScore == null) {
                favoriteMatchMatchStrip.visibility = View.GONE
                favoriteMatchHomeScore.text = ""
                favoriteMatchAwayScore.text = ""
            } else {
                favoriteMatchMatchStrip.visibility = View.VISIBLE
                favoriteMatchHomeScore.text = favorite.matchHomeScore.toString()
                favoriteMatchAwayScore.text = favorite.matchAwayScore.toString()
            }

            favoriteMatchHomeTeam.text = favorite.matchHomeTeam
            favoriteMatchAwayTeam.text = favorite.matchAwayTeam

            parentLayout.setOnClickListener {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.apply {
                    putExtra(MatchDetailActivity.EXTRA_MATCH_ID, favorite.matchId)
                }
                context.startActivity(intent)
            }
        }
    }
}