package com.example.leagueapptest.domaine.Repository.deatil.manager

import com.example.leagueapptest.domaine.Repository.deatil.model.DetailTeamRepoModel
import com.example.leagueapptest.domaine.Repository.loadLeague.model.LeagueRepoModel
import com.example.leagueapptest.domaine.Repository.loadLeague.model.TeamRepoModel
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DetailTeamRepo {
    fun loadTeamById(teamId:Int, scope: CoroutineScope):Flow<DataState<DetailTeamRepoModel>>
}