package io.github.mrizkifadil26.footballmatchschedule.repository

import io.github.mrizkifadil26.footballmatchschedule.model.data.League
import io.github.mrizkifadil26.footballmatchschedule.model.data.LeagueInfo
import io.github.mrizkifadil26.footballmatchschedule.network.ApiService
import io.github.mrizkifadil26.footballmatchschedule.network.RetrofitBuilder

class LeagueRepository {

    private val apiService: ApiService = RetrofitBuilder.apiService

    suspend fun getLeagueFromRetrofit(countryQuery: String,
                                      sportQuery: String
    ): List<League>? {
        val response = apiService.getLeague(countryQuery, sportQuery)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.countrys
        }
        return null
    }

    suspend fun getLeagueInfoFromRetrofit(idLeague: Int
    ): List<LeagueInfo>? {
        val response = apiService.getLeagueInfo(idLeague)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.league
        }

        return null
    }

}