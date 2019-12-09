package io.github.mrizkifadil26.footballmatchschedule.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.fragment.league.LeagueInfoFragment
import io.github.mrizkifadil26.footballmatchschedule.fragment.match.MatchFragment
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        val leagueId = intent.getIntExtra(EXTRA_ID, 0)

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment

            when (item.itemId) {
                R.id.navigation_info -> {
                    fragment = LeagueInfoFragment(leagueId)
                    supportActionBar?.title = getString(R.string.league_info)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.nav_host,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_match -> {
                    fragment = MatchFragment(leagueId)
                    supportActionBar?.title = getString(R.string.matches)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.nav_host,
                        fragment,
                        fragment.javaClass.simpleName
                    ).detach(fragment)
                        .attach(fragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false
        }

        if (savedInstanceState == null) {
            bottom_nav.selectedItemId = R.id.navigation_info
        }
    }
}
