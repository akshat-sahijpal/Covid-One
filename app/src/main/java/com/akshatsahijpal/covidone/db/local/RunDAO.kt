package com.akshatsahijpal.covidone.db.local

import androidx.paging.PagingSource
import androidx.room.*
import com.akshatsahijpal.covidone.data.CovidData

@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRun(userData: CovidData)

    @Delete
    suspend fun deleteRun(userData: CovidData)

    @Query("SELECT * FROM covid_table")
    fun getAllData(): List<CovidData>

    @Query("SELECT * FROM covid_table WHERE StateCity LIKE :query")
    fun getSearchResult(query: String): PagingSource<Int, CovidData>

    @Query("SELECT * FROM covid_table WHERE StateCity LIKE :query AND Resource LIKE :product")
    fun getSearchResultForSupply(query: String, product: String): PagingSource<Int, CovidData>

    @Query("SELECT * FROM covid_table WHERE Resource LIKE :product")
    fun getDefaultData(product: String): PagingSource<Int, CovidData>
    fun deleteDir()
}