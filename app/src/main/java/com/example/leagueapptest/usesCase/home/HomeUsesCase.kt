package com.example.leagueapptest.usesCase.home

import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.flow.Flow

interface HomeUsesCase {
    fun loadLeagueList():Flow<DataState<List<LeagueUiModel>>>
    fun loadLeagueTeamList(leagueId:Int):Flow<DataState<List<TeamUiModel>>>
}