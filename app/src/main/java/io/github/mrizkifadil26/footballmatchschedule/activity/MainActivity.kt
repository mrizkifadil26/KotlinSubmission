package io.github.mrizkifadil26.footballmatchschedule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.fragment.league.LeagueFragment
import io.github.mrizkifadil26.footballmatchschedule.fragment.match.FavoriteMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_main_nav.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment

            when(item.itemId) {
                R.id.main_nav_home -> {
                    supportActionBar?.title = getString(R.string.leagues)
                    fragment = LeagueFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.host_fragment,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.main_nav_favorite -> {
                    supportActionBar?.title = getString(R.string.favorite_match)
                    fragment = FavoriteMatchFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.host_fragment,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false
        }

        if (savedInstanceState == null) {
            bottom_main_nav.selectedItemId = R.id.main_nav_home
        }
    }
}
