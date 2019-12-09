package io.github.mrizkifadil26.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.model.data.UpcomingMatch

class UpcomingMatchAdapter(val context: Context,
                           private val matches: List<UpcomingMatch>
) : RecyclerView.Adapter<UpcomingMatchAdapter.UpcomingMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMatchViewHolder{
        return UpcomingMatchViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_upcoming_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: UpcomingMatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }

    inner class UpcomingMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val matchHomeTeam = view.findViewById<TextView>(R.id.upcoming_match_home_team)
        private val matchAwayTeam= view.findViewById<TextView>(R.id.upcoming_match_away_team)
        private val matchRound = view.findViewById<TextView>(R.id.upcoming_match_round)
        private val matchDate = view.findViewById<TextView>(R.id.upcoming_match_date)
        private val matchTime = view.findViewById<TextView>(R.id.upcoming_match_time)

        fun bindItem(match: UpcomingMatch) {

            matchRound.text = String.format(context.getString(R.string.match), match.round)
            matchHomeTeam.text = match.homeTeam
            matchAwayTeam.text = match.awayTeam
            matchDate.text = match.date
            matchTime.text = match.time
        }
    }
}