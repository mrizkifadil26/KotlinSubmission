package io.github.mrizkifadil26.footballmatchschedule.db

data class FavoriteMatchDatabase(
    val id: Long?,
    val matchId: Int?,
    val matchSport: String?,
    val matchLeague: String,
    val matchDate: String?,
    val matchTime: String?,
    val matchRound: Int?,
    val matchHomeTeam: String?,
    val matchAwayTeam: String?,
    val matchHomeScore: Int?,
    val matchAwayScore: Int?,
    val matchHomeGoals: String?,
    val matchAwayGoals: String?,
    val matchHomeYellowCards: String?,
    val matchAwayYellowCards: String?,
    val matchHomeRedCards: String?,
    val matchAwayRedCards: String?,
    val matchHomeGoalkeeper: String?,
    val matchHomeDefender: String?,
    val matchHomeMidfielder: String?,
    val matchHomeForward: String?,
    val matchHomeSubstitutes: String?,
    val matchAwayGoalkeeper: String?,
    val matchAwayDefender: String?,
    val matchAwayMidfielder: String?,
    val matchAwayForward: String?,
    val matchAwaySubstitutes: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_SPORT: String = "MATCH_SPORT"
        const val MATCH_LEAGUE: String = "MATCH_LEAGUE"
        const val MATCH_ROUND: String = "MATCH_ROUND"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"

        const val MATCH_HOME_TEAM: String = "MATCH_HOME_TEAM"
        const val MATCH_AWAY_TEAM: String = "MATCH_AWAY_TEAM"

        const val MATCH_HOME_SCORE: String = "MATCH_HOME_SCORE"
        const val MATCH_AWAY_SCORE: String = "MATCH_AWAY_SCORE"

        const val MATCH_HOME_GOALS: String = "MATCH_HOME_GOALS"
        const val MATCH_AWAY_GOALS: String = "MATCH_AWAY_GOALS"

        const val MATCH_HOME_YELLOW_CARDS: String = "MATCH_HOME_YELLOW_CARDS"
        const val MATCH_AWAY_YELLOW_CARDS: String = "MATCH_AWAY_YELLOW_CARDS"

        const val MATCH_HOME_RED_CARDS: String = "MATCH_HOME_RED_CARDS"
        const val MATCH_AWAY_RED_CARDS: String = "MATCH_AWAY_RED_CARDS"

        const val MATCH_HOME_GOALKEEPER: String = "MATCH_HOME_GOALKEEPER"
        const val MATCH_HOME_DEFENDER: String = "MATCH_HOME_DEFENDER"
        const val MATCH_HOME_MIDFIELDER: String = "MATCH_HOME_MIDFIELDER"
        const val MATCH_HOME_FORWARD: String = "MATCH_HOME_FORWARD"
        const val MATCH_HOME_SUBSTITUTES: String = "MATCH_HOME_SUBSTITUTES"

        const val MATCH_AWAY_GOALKEEPER: String = "MATCH_AWAY_GOALKEEPER"
        const val MATCH_AWAY_DEFENDER: String = "MATCH_AWAY_DEFENDER"
        const val MATCH_AWAY_MIDFIELDER: String = "MATCH_AWAY_MIDFIELDER"
        const val MATCH_AWAY_FORWARD: String = "MATCH_AWAY_FORWARD"
        const val MATCH_AWAY_SUBSTITUTES: String = "MATCH_AWAY_SUBSTITUTES"
    }
}