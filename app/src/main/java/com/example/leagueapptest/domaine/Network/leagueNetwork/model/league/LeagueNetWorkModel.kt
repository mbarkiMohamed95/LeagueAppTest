package com.example.leagueapptest.domaine.Network.leagueNetwork.model.league

import com.google.gson.annotations.SerializedName

data class LeagueNetWorkModel(
    @SerializedName("idLeague")   val idLeague: Int?,
    @SerializedName("strLeague")  val strLeague: String?,
    @SerializedName("strSport") val strSport: String?,
    @SerializedName("strLeagueAlternate") val strLeagueAlternate: String?
)
