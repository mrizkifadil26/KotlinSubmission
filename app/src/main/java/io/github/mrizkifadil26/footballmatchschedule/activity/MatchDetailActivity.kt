package io.github.mrizkifadil26.footballmatchschedule.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail
import io.github.mrizkifadil26.footballmatchschedule.presenter.MatchDetailPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.util.Config.dateFormatter
import io.github.mrizkifadil26.footballmatchschedule.view.MatchDetailView
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    companion object {
        const val EXTRA_MATCH_ID = "extra_match_id"
    }

    val repository: MatchRepository = MatchRepository()

    lateinit var presenter: MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.apply {
            title = getString(R.string.match_info_title)
        }

        val idMatch = intent.getIntExtra(EXTRA_MATCH_ID, 0)

        presenter = MatchDetailPresenter(this, repository)
        presenter.getMatchDetail(idMatch)
    }

    override fun showSpinner() {
        progress_match_detail.visibility = View.VISIBLE
        match_detail_info_layout.visibility = View.GONE
        match_detail_squad_layout.visibility = View.GONE
        match_detail_substitutes_layout.visibility = View.GONE
    }

    override fun hideSpinner() {
        progress_match_detail.visibility = View.GONE
        match_detail_info_layout.visibility = View.VISIBLE
        match_detail_squad_layout.visibility = View.VISIBLE
        match_detail_substitutes_layout.visibility = View.VISIBLE
    }

    override fun showMatchDetail(data: MatchDetail) {
        match_detail_info.text = String.format(getString(R.string.match_info), data.sport)

        if (data.round != 0) {
            match_detail_round.text = String.format(getString(R.string.match_round), data.league, data.round)
        } else {
            match_detail_round.text = data.league
        }

        match_detail_date.text = dateFormatter(data.matchDate, "dd/MM/YYYY")
        match_detail_time.text = data.time

        match_detail_home_score.text = data.homeScore.toString()
        match_detail_away_score.text = data.awayScore.toString()

        match_detail_home_team.text = data.homeTeam
        match_detail_away_team.text = data.awayTeam

        if (data.homeScore == null && data.awayScore == null) {
            match_detail_icon_ball.visibility = View.GONE
            match_detail_strip.visibility = View.GONE
            match_detail_home_score.text = ""
            match_detail_away_score.text = ""
        } else {
            if (data.homeScore == 0 && data.homeScore == 0) {
                match_detail_icon_ball.visibility = View.GONE
            } else {
                match_detail_icon_ball.visibility = View.VISIBLE
                match_detail_home_goals.text = data.homeGoals
                match_detail_away_goals.text = data.awayGoals
            }
        }

        if (data.homeYellowCards == null && data.awayYellowCards == null) {
            match_detail_icon_yellow_card.visibility = View.GONE
        } else {
            if (data.homeYellowCards == "" && data.awayYellowCards == "") {
                match_detail_icon_yellow_card.visibility = View.GONE
            } else {
                match_detail_icon_yellow_card.visibility = View.VISIBLE
                match_detail_home_yellow_cards.text = data.homeYellowCards
                match_detail_away_yellow_cards.text = data.awayYellowCards
            }
        }


        if (data.homeRedCards == null && data.awayRedCards == null) {
            match_detail_icon_red_card.visibility = View.GONE
        } else {
            if (data.homeRedCards == "" && data.awayRedCards == "") {
                match_detail_icon_red_card.visibility = View.GONE
            } else {
                match_detail_icon_red_card.visibility = View.VISIBLE
                match_detail_home_red_cards.text = data.homeRedCards
                match_detail_away_red_cards.text = data.awayRedCards
            }
        }

        if (data.homeGoalkeeper == null || data.homeDefender == null ||
            data.homeMidfielder == null || data.homeForward == null ||
            data.awayGoalkeeper== null || data.awayDefender == null ||
            data.awayMidfielder == null || data.awayForward == null
        ) {
            match_detail_squad_layout.visibility = View.GONE
        } else {
            if (data.homeGoalkeeper == "" || data.homeDefender == "" ||
                data.homeMidfielder == "" || data.homeForward == "" ||
                data.awayGoalkeeper== "" || data.awayDefender == "" ||
                data.awayMidfielder == "" || data.awayForward == ""
            ) {
                match_detail_squad_layout.visibility = View.GONE
            } else {
                match_detail_squad_layout.visibility = View.VISIBLE
                match_detail_home_goalkeeper.text = data.homeGoalkeeper
                match_detail_home_defender.text = data.homeDefender
                match_detail_home_midfielder.text = data.homeMidfielder
                match_detail_home_forward.text = data.homeForward

                match_detail_away_goalkeeper.text = data.awayGoalkeeper
                match_detail_away_defender.text = data.awayDefender
                match_detail_away_midfielder.text = data.awayMidfielder
                match_detail_away_forward.text = data.awayForward
            }

        }

        if (data.homeSubstitutes == null || data.awaySubstitutes == null) {
            match_detail_substitutes_layout.visibility = View.GONE
        } else {
            if (data.homeSubstitutes == "" || data.awaySubstitutes == "") {
                match_detail_substitutes_layout.visibility = View.GONE
            } else {
                match_detail_home_substitutes.text = data.homeSubstitutes
                match_detail_away_substitutes.text = data.awaySubstitutes
            }
        }
    }
}
