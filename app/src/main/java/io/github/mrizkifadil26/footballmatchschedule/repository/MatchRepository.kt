package io.github.mrizkifadil26.footballmatchschedule.repository

import io.github.mrizkifadil26.footballmatchschedule.model.data.LastMatch
import io.github.mrizkifadil26.footballmatchschedule.model.data.MatchDetail
import io.github.mrizkifadil26.footballmatchschedule.model.data.UpcomingMatch
import io.github.mrizkifadil26.footballmatchschedule.network.ApiService
import io.github.mrizkifadil26.footballmatchschedule.network.RetrofitBuilder

class MatchRepository {

    private val apiService: ApiService = RetrofitBuilder.apiService

    suspend fun getLastMatchFromRetrofit(idLeague: Int
    ): List<LastMatch>? {
        val response = apiService.getLastMatch(idLeague)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.lastMatches
        }

        return null
    }

    suspend fun getUpcomingMatchFromRetrofit(idLeague: Int
    ): List<UpcomingMatch>? {
        val response = apiService.getUpcomingMatch(idLeague)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.upcomingMatches
        }

        return null
    }

    suspend fun getMatchDetailFromRetrofit(
        idMatch: Int
    ): List<MatchDetail>? {
        val response = apiService.getMatchDetail(idMatch)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.matches
        }

        return null
    }

    suspend fun getMatchByQueryFromRetrofit(
        query: String
    ): List<MatchDetail>? {
        val response = apiService.getMatchByQuery(query)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.matches
        }

        return null
    }

}