package com.example.leagueapptest.presentation.homeWithMVP.interaction

import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel

interface HomeFragment {
    fun presentLeagueList(data : DataState<List<LeagueUiModel>>)
    fun presentLeagueTeamList(data : DataState<List<TeamUiModel>>)
}