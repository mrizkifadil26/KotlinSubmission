package io.github.mrizkifadil26.footballmatchschedule.fragment.league

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.model.data.LeagueInfo
import io.github.mrizkifadil26.footballmatchschedule.presenter.LeagueInfoPresenter
import io.github.mrizkifadil26.footballmatchschedule.repository.LeagueRepository
import io.github.mrizkifadil26.footballmatchschedule.util.Config.dateFormatter
import io.github.mrizkifadil26.footballmatchschedule.view.LeagueInfoView
import kotlinx.android.synthetic.main.fragment_league_info.*


class LeagueInfoFragment(
    private val leagueId: Int
) : Fragment(), LeagueInfoView {

    private val repository: LeagueRepository = LeagueRepository()
    private lateinit var uri: Uri
    private lateinit var intent: Intent

    lateinit var presenter: LeagueInfoPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = LeagueInfoPresenter(this, repository)
        presenter.getLeagueInfo(leagueId)
    }

    override fun showSpinner() {
        progress_league_info.visibility = View.VISIBLE
        img_banner.visibility = View.GONE
        card_info.visibility = View.GONE
        card_description.visibility = View.GONE
        card_social.visibility = View.GONE
    }

    override fun hideSpinner() {
        progress_league_info.visibility = View.GONE
        img_banner.visibility = View.VISIBLE
        card_info.visibility = View.VISIBLE
        card_description.visibility = View.VISIBLE
        card_social.visibility = View.VISIBLE
    }

    override fun showLeagueInfo(data: LeagueInfo) {
        if (data.banner == null) {
            img_banner.setBackgroundColor(Color.GRAY)
        } else {
            Picasso
                .get()
                .load(data.banner)
                .into(img_banner)
        }

        Picasso
            .get()
            .load(data.logo)
            .into(img_logo)

        league_title.text = data.leagueName
        league_country.text = data.countryName
        league_division.text = (data.division + 1).toString()
        league_first_match.text = dateFormatter(data.firstEvent, "dd MMM yyyy")
        league_formed.text = data.formedYear.toString()

        text_description.text = data.description

        btnClick(btn_facebook, data.facebook)
        btnClick(btn_web, data.website)
        btnClick(btn_twitter, data.twitter)
        btnClick(btn_youtube, data.youtube)
    }

    private fun btnClick(button: ImageButton, data: String) {
        if (data.isEmpty()) {
            button.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                dialog.apply {
                    setMessage(getString(R.string.link_not_found))
                }
                dialog.show()
            }
        } else {
            button.setOnClickListener {
                uri = Uri.parse("https://$data")
                intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    intent.setPackage(null)
                    startActivity(intent)
                }
            }
        }
    }
}
