package com.example.leagueapptest.domaine.Network.service

import com.example.leagueapptest.domaine.Network.leagueNetwork.model.league.LeagueTeamResponseModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.ResponseTeamModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("/api/v1/json/{apikey}/all_leagues.php")
    fun loadLeagueList(@Path("apikey") apikey:String): Call<LeagueTeamResponseModel>

    @GET("/api/v1/json/{apikey}/lookup_all_teams.php")
    fun loadLeagueTeamList(@Path("apikey") apikey:String,@Query("id") leagueId:Int): Call<ResponseTeamModel>

    @GET("/api/v1/json/{apikey}/lookupteam.php")
    fun loadTeamById(@Path("apikey") apikey:String,@Query("id") TeamId:Int): Call<ResponseTeamModel>
}