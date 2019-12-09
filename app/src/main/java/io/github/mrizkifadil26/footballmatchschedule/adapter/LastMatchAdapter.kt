package io.github.mrizkifadil26.footballmatchschedule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.activity.MatchDetailActivity
import io.github.mrizkifadil26.footballmatchschedule.model.data.LastMatch

class LastMatchAdapter(val context: Context,
                       private val matches: List<LastMatch>
) : RecyclerView.Adapter<LastMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder{
        return LastMatchViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_last_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }

    inner class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val parentLayout = view.findViewById<CardView>(R.id.last_match_layout)
        private val matchHomeTeam= view.findViewById<TextView>(R.id.last_match_home_team)
        private val matchAwayTeam= view.findViewById<TextView>(R.id.last_match_away_team)
        private val matchRound = view.findViewById<TextView>(R.id.last_match_round)
        private val matchDate = view.findViewById<TextView>(R.id.last_match_date)
        private val matchTime = view.findViewById<TextView>(R.id.last_match_time)

        private val matchHomeScore = view.findViewById<TextView>(R.id.last_match_home_score)
        private val matchAwayScore = view.findViewById<TextView>(R.id.last_match_away_score)
        private val matchHomeGoal = view.findViewById<TextView>(R.id.last_match_home_goal)
        private val matchAwayGoal = view.findViewById<TextView>(R.id.last_match_away_goal)

        private val matchIconBall = view.findViewById<ImageView>(R.id.last_match_icon_ball)

        fun bindItem(match: LastMatch) {

            matchRound.text = String.format(context.getString(R.string.match), match.round)
            matchDate.text = match.date
            matchTime.text = match.time

            matchHomeTeam.text = match.homeTeam
            matchAwayTeam.text = match.awayTeam

            matchHomeScore.text = match.homeScore.toString()
            matchAwayScore.text = match.awayScore.toString()

            if (match.homeScore == 0 && match.awayScore == 0) {
                matchIconBall.visibility = View.GONE
            } else {
                matchIconBall.visibility = View.VISIBLE
                matchHomeGoal.text = match.homeGoals
                matchAwayGoal.text = match.awayGoals
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
