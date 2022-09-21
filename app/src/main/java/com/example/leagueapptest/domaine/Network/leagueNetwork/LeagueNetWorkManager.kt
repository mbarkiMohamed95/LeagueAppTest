package com.example.leagueapptest.domaine.Network.leagueNetwork

import com.example.leagueapptest.domaine.Network.leagueNetwork.model.league.LeagueNetWorkModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.Teams
import kotlinx.coroutines.flow.Flow

interface LeagueNetWorkManager {
    fun loadLeagueList(): Flow<List<LeagueNetWorkModel>?>
    fun loadLeagueTeamList(leagueId:Int): Flow<List<Teams>?>
}