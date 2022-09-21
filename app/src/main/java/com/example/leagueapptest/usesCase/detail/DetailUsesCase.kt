package com.example.leagueapptest.usesCase.detail

import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.detail.model.DetailTeamUiModel
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DetailUsesCase {
    fun loadTeamById(leagueId:Int,scope: CoroutineScope):Flow<DataState<DetailTeamUiModel>>
}