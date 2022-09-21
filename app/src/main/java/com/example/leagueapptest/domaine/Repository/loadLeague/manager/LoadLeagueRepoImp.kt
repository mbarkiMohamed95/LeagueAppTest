package com.example.leagueapptest.domaine.Repository.loadLeague.manager

import com.example.leagueapptest.domaine.Network.leagueNetwork.LeagueNetWorkManager
import com.example.leagueapptest.domaine.Network.leagueNetwork.LeagueNetWorkManagerImp
import com.example.leagueapptest.domaine.Repository.loadLeague.model.LeagueRepoModel
import com.example.leagueapptest.domaine.Repository.loadLeague.model.TeamRepoModel
import com.example.leagueapptest.tools.dataState.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadLeagueRepoImp constructor(private val netWorkManager: LeagueNetWorkManager) :
    LoadLeagueRepo {
    override fun loadLeagueList(): Flow<DataState<List<LeagueRepoModel>>> = flow {
        netWorkManager.loadLeagueList().collect { res ->
            res?.let {
                var mapedList = res.map { LeagueRepoModel(it.idLeague, it.strLeagueAlternate) }
                emit(DataState.Success(mapedList))
            } ?: kotlin.run {
                emit(DataState.Error(Exception("null")))
            }
        }
    }

    override fun loadLeagueTeamList(leagueId: Int): Flow<DataState<List<TeamRepoModel>>> = flow {
        netWorkManager.loadLeagueTeamList(leagueId).collect { res ->
            res?.let {
                var mapedTeamList = res.map { TeamRepoModel(it.idTeam, it.strTeamBadge) }
                emit(DataState.Success(mapedTeamList))
            } ?: kotlin.run {
                emit(DataState.Error(Exception("null")))
            }
        }
    }

    companion object {
        private val loadLeagueRepo: LoadLeagueRepo by lazy {
            LoadLeagueRepoImp(LeagueNetWorkManagerImp.getLeagueNetWorkManagerInstance())
        }

        fun getLoadLeagueRepoImp(): LoadLeagueRepo = loadLeagueRepo
    }
}