package com.example.leagueapptest.domaine.Repository.loadLeague.manager

import com.example.leagueapptest.domaine.Repository.loadLeague.model.LeagueRepoModel
import com.example.leagueapptest.domaine.Repository.loadLeague.model.TeamRepoModel
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.flow.Flow

interface LoadLeagueRepo {
    fun loadLeagueList(): Flow<DataState<List<LeagueRepoModel>>>
    fun loadLeagueTeamList(leagueId:Int):Flow<DataState<List<TeamRepoModel>>>

}