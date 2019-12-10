package io.github.mrizkifadil26.footballmatchschedule.fragment.match


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import io.github.mrizkifadil26.footballmatchschedule.R
import io.github.mrizkifadil26.footballmatchschedule.adapter.FavoriteMatchAdapter
import io.github.mrizkifadil26.footballmatchschedule.db.FavoriteMatchDatabase
import io.github.mrizkifadil26.footballmatchschedule.db.database
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<FavoriteMatchDatabase> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMatchAdapter(view.context, favorites)
        recycler_favorite_match.apply {
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteMatchDatabase.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatchDatabase>())
            if (favorite.isNullOrEmpty()) {
                recycler_favorite_match.visibility = View.GONE
                favorite_match_status.visibility = View.VISIBLE
                favorite_match_status.text = getString(R.string.no_data)
            } else {
                recycler_favorite_match.visibility = View.VISIBLE
                recycler_favorite_match.adapter = adapter
                favorites.addAll(favorite)
                adapter.notifyDataSetChanged()
            }

        }
    }
}
