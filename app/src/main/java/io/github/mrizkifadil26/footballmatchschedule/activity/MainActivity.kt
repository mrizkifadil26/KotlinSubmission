package io.github.mrizkifadil26.footballmatchschedule.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.fragment.league.LeagueFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.app_name)

        val fragment = LeagueFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.host_fragment,
            fragment,
            fragment.javaClass.simpleName
        ).commit()
    }
}
