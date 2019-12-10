package io.github.mrizkifadil26.footballmatchschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavoriteDatabaseOpenHelper(
    context: Context
) : ManagedSQLiteOpenHelper(
    context,
    "FavoriteMatch.db",
    null, 1) {

    companion object {
        private var instance: FavoriteDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): FavoriteDatabaseOpenHelper {
            if (instance == null) {
                instance = FavoriteDatabaseOpenHelper(context.applicationContext)
            }

            return instance as FavoriteDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatchDatabase.TABLE_FAVORITE, true,
            FavoriteMatchDatabase.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatchDatabase.MATCH_ID to INTEGER,
            FavoriteMatchDatabase.MATCH_SPORT to TEXT,
            FavoriteMatchDatabase.MATCH_LEAGUE to TEXT,
            FavoriteMatchDatabase.MATCH_DATE to TEXT,
            FavoriteMatchDatabase.MATCH_TIME to TEXT,
            FavoriteMatchDatabase.MATCH_ROUND to INTEGER,
            FavoriteMatchDatabase.MATCH_HOME_TEAM to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_TEAM to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_SCORE to INTEGER,
            FavoriteMatchDatabase.MATCH_AWAY_SCORE to INTEGER,
            FavoriteMatchDatabase.MATCH_HOME_GOALS to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_GOALS to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_YELLOW_CARDS to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_YELLOW_CARDS to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_RED_CARDS to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_RED_CARDS to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_GOALKEEPER to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_DEFENDER to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_MIDFIELDER to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_FORWARD to TEXT,
            FavoriteMatchDatabase.MATCH_HOME_SUBSTITUTES to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_GOALKEEPER to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_DEFENDER to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_MIDFIELDER to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_FORWARD to TEXT,
            FavoriteMatchDatabase.MATCH_AWAY_SUBSTITUTES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatchDatabase.TABLE_FAVORITE, true)
    }
}

val Context.database: FavoriteDatabaseOpenHelper
    get() = FavoriteDatabaseOpenHelper.getInstance(applicationContext)