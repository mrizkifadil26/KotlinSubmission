package io.github.mrizkifadil26.footballmatchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.db.FavoriteMatchDatabase
import io.github.mrizkifadil26.footballmatchschedule.db.database
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail
import io.github.mrizkifadil26.footballmatchschedule.presenter.MatchDetailPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.MatchRepository
import io.github.mrizkifadil26.footballmatchschedule.util.Config.dateFormatter
import io.github.mrizkifadil26.footballmatchschedule.view.MatchDetailView
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    companion object {
        const val EXTRA_MATCH_ID = "extra_match_id"
    }

    val repository: MatchRepository = MatchRepository()

    lateinit var presenter: MatchDetailPresenter

    private var matchId: Int = 0
    private lateinit var matchDetail: MatchDetail

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.apply {
            title = getString(R.string.match_info_title)
        }

        matchId = intent.getIntExtra(EXTRA_MATCH_ID, 0)

        favoriteState()

        presenter = MatchDetailPresenter(this, repository)
        presenter.getMatchDetail(matchId)
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

    override fun showMatchDetail(data: MatchDetail?) {

        matchDetail = MatchDetail(
            data?.id,
            data?.sport,
            data?.league,
            data?.homeTeam,
            data?.awayTeam,
            data?.round,
            data?.date,
            data?.time,
            data?.homeScore,
            data?.awayScore,
            data?.homeGoals,
            data?.awayGoals,
            data?.homeYellowCards,
            data?.awayYellowCards,
            data?.homeRedCards,
            data?.awayRedCards,
            data?.homeGoalkeeper,
            data?.homeDefender,
            data?.homeMidfielder,
            data?.homeForward,
            data?.homeSubstitutes,
            data?.awayGoalkeeper,
            data?.awayDefender,
            data?.awayMidfielder,
            data?.awayForward,
            data?.awaySubstitutes
        )

        match_detail_info.text = String.format(getString(R.string.match_info), data?.sport)

        if (data?.round != 0) {
            match_detail_round.text = String.format(getString(R.string.match_round), data?.league, data?.round)
        } else {
            match_detail_round.text = data.league
        }

        match_detail_date.text = data?.date?.let {
            dateFormatter(it, "dd/MM/YYYY")
        }
        match_detail_time.text = data?.time

        match_detail_home_score.text = data?.homeScore.toString()
        match_detail_away_score.text = data?.awayScore.toString()

        match_detail_home_team.text = data?.homeTeam
        match_detail_away_team.text = data?.awayTeam

        if (data?.homeScore != null && data.awayScore != null) {
            match_detail_home_score.text = data.homeScore.toString()
            match_detail_away_score.text = data.awayScore.toString()

            if (data.homeScore == 0 && data.awayScore == 0) {
                match_detail_icon_ball.visibility = View.GONE
                match_detail_home_goals.text = ""
                match_detail_away_goals.text = ""

            } else {
                if (data.homeGoals != null && data.awayGoals!= null) {
                    if (data.homeGoals == "" && data.awayGoals == "") {
                        match_detail_icon_ball.visibility = View.GONE

                        match_detail_home_goals.text = data.homeGoals
                        match_detail_away_goals.text = data.awayGoals
                    } else {
                        match_detail_icon_ball.visibility = View.VISIBLE

                        match_detail_home_goals.text = data.homeGoals
                        match_detail_away_goals.text = data.awayGoals
                    }
                } else {
                    match_detail_icon_ball.visibility = View.GONE
                }
            }
        } else {
            match_detail_icon_ball.visibility = View.GONE
            match_detail_home_score.text = ""
            match_detail_away_score.text = ""

            match_detail_home_goals.text = ""
            match_detail_away_goals.text = ""
        }

        if (data?.homeYellowCards == null && data?.awayYellowCards == null) {
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


        if (data?.homeRedCards == null && data?.awayRedCards == null) {
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

        if (data?.homeGoalkeeper == null || data.homeDefender == null ||
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

        if (data?.homeSubstitutes == null || data.awaySubstitutes == null) {
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

    override fun showStatus() {
        match_detail_status.visibility = View.VISIBLE
        match_detail_status.text = getString(R.string.no_data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_match_detail, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_add_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatchDatabase.TABLE_FAVORITE,
                    FavoriteMatchDatabase.MATCH_ID to matchDetail.id,
                    FavoriteMatchDatabase.MATCH_SPORT to matchDetail.sport,
                    FavoriteMatchDatabase.MATCH_LEAGUE to matchDetail.league,
                    FavoriteMatchDatabase.MATCH_ROUND to matchDetail.round,
                    FavoriteMatchDatabase.MATCH_DATE to matchDetail.date,
                    FavoriteMatchDatabase.MATCH_TIME to matchDetail.time,
                    FavoriteMatchDatabase.MATCH_HOME_TEAM to matchDetail.homeTeam,
                    FavoriteMatchDatabase.MATCH_AWAY_TEAM to matchDetail.awayTeam,
                    FavoriteMatchDatabase.MATCH_HOME_SCORE to matchDetail.homeScore,
                    FavoriteMatchDatabase.MATCH_AWAY_SCORE to matchDetail.awayScore,
                    FavoriteMatchDatabase.MATCH_HOME_GOALS to matchDetail.homeGoals,
                    FavoriteMatchDatabase.MATCH_AWAY_GOALS to matchDetail.awayGoals,
                    FavoriteMatchDatabase.MATCH_HOME_YELLOW_CARDS to matchDetail.homeYellowCards,
                    FavoriteMatchDatabase.MATCH_AWAY_YELLOW_CARDS to matchDetail.awayYellowCards,
                    FavoriteMatchDatabase.MATCH_HOME_RED_CARDS to matchDetail.homeRedCards,
                    FavoriteMatchDatabase.MATCH_AWAY_RED_CARDS to matchDetail.awayRedCards,
                    FavoriteMatchDatabase.MATCH_HOME_GOALKEEPER to matchDetail.homeGoalkeeper,
                    FavoriteMatchDatabase.MATCH_HOME_DEFENDER to matchDetail.homeDefender,
                    FavoriteMatchDatabase.MATCH_HOME_MIDFIELDER to matchDetail.homeMidfielder,
                    FavoriteMatchDatabase.MATCH_HOME_FORWARD to matchDetail.homeForward,
                    FavoriteMatchDatabase.MATCH_HOME_SUBSTITUTES to matchDetail.homeSubstitutes,
                    FavoriteMatchDatabase.MATCH_AWAY_GOALKEEPER to matchDetail.awayGoalkeeper,
                    FavoriteMatchDatabase.MATCH_AWAY_DEFENDER to matchDetail.awayDefender,
                    FavoriteMatchDatabase.MATCH_AWAY_MIDFIELDER to matchDetail.awayMidfielder,
                    FavoriteMatchDatabase.MATCH_AWAY_FORWARD to matchDetail.awayForward,
                    FavoriteMatchDatabase.MATCH_AWAY_SUBSTITUTES to matchDetail.awaySubstitutes
                )
            }
            Snackbar.make(match_detail_layout, "Added to Favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(match_detail_layout, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMatchDatabase.TABLE_FAVORITE,
                    "(MATCH_ID = {id})",
                    "id" to matchId
                )
            }
            Snackbar.make(match_detail_layout, getString(R.string.removed_favorite), Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(match_detail_layout, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = resources.getDrawable(R.drawable.ic_favorite_added, this.theme)
        else
            menuItem?.getItem(0)?.icon = resources.getDrawable(R.drawable.ic_favorite_add, this.theme)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatchDatabase.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to matchId
                )
            val favorite = result.parseList(classParser<FavoriteMatchDatabase>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }
}
