package com.example.leagueapptest.domaine.Network.detailTeam

import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.Teams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DetailTeamManager {
    fun loadTeamById(teamId:Int, scope: CoroutineScope): Flow<List<Teams>?>

}