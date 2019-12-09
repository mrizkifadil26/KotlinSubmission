package io.github.mrizkifadil26.footballmatchschedule.network

import io.github.mrizkifadil26.footballmatchschedule.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search_all_leagues.php")
    suspend fun getLeague(
        @Query("c") countryQuery: String,
        @Query("s") sportQuery: String
    ) : Response<LeagueResponse>

    @GET("lookupleague.php")
    suspend fun getLeagueInfo(
        @Query("id") idLeague: Int
    ) : Response<LeagueInfoResponse>

    @GET("eventsnextleague.php")
    suspend fun getUpcomingMatch(
        @Query("id") idLeague: Int
    ) : Response<UpcomingMatchResponse>

    @GET("eventspastleague.php")
    suspend fun getLastMatch(
        @Query("id") idLeague: Int
    ) : Response<LastMatchResponse>

    @GET("lookupevent.php")
    suspend fun getMatchDetail(
        @Query("id") idMatch: Int
    ) : Response<MatchDetailResponse>

    @GET("searchevents.php")
    suspend fun getMatchByQuery(
        @Query("e") matchQuery: String
    ) : Response<MatchQueryResponse>
}