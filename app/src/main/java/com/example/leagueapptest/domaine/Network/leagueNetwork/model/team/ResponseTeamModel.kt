package com.example.leagueapptest.domaine.Network.leagueNetwork.model.team

import com.google.gson.annotations.SerializedName


data class ResponseTeamModel (
	@SerializedName("teams") val teams : List<Teams>
)