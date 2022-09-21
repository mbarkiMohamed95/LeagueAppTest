package com.example.leagueapptest.usesCase.home

import com.example.leagueapptest.domaine.Repository.loadLeague.manager.LoadLeagueRepo
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class HomeUsesCaseImp(private val loadLeagueRepo: LoadLeagueRepo) : HomeUsesCase {
    override fun loadLeagueList(): Flow<DataState<List<LeagueUiModel>>> = flow {
        loadLeagueRepo.loadLeagueList().collect {
            when (it) {
                is DataState.Success -> {
                    var leagueMapedList = it.data.map { LeagueUiModel(it.LeagueId, it.leagueName) }
                    emit(DataState.Success(leagueMapedList))
                }
                is DataState.Error -> {
                    emit(it)
                }
            }
        }
    }

    override fun loadLeagueTeamList(leagueId: Int): Flow<DataState<List<TeamUiModel>>> = flow {
        loadLeagueRepo.loadLeagueTeamList(leagueId).collect {
            when (it) {
                is DataState.Success -> {
                    var leagueMapedList = it.data.map { TeamUiModel(it.teamId, it.teamLogo) }
                    emit(DataState.Success(leagueMapedList))
                }
                is DataState.Error -> {
                    emit(it)
                }
            }
        }
    }
}