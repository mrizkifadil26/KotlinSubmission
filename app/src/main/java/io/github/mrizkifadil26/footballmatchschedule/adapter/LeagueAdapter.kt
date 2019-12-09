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
import com.squareup.picasso.Picasso
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.activity.LeagueActivity
import io.github.mrizkifadil26.footballmatchschedule.model.data.League

class LeagueAdapter(val context: Context, private val league: List<League>)
    : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_league,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = league.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(league[position])
    }

    inner class LeagueViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        private val parentLayout = view.findViewById<CardView>(R.id.parent_layout)
        private val leagueLogo = view.findViewById<ImageView>(R.id.league_logo)
        private val leagueName = view.findViewById<TextView>(R.id.league_name)

        fun bindItem(leagues: League) {
            Picasso
                .get()
                .load(leagues.logo)
                .into(leagueLogo)

            leagueName.text = leagues.title

            parentLayout.setOnClickListener {
                val intent = Intent(context, LeagueActivity::class.java)
                intent.apply {
                    putExtra(LeagueActivity.EXTRA_ID, leagues.id)
                }
                context.startActivity(intent)
            }
        }
    }

}