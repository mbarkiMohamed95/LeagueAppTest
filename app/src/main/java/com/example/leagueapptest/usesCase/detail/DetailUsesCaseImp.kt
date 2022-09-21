package com.example.leagueapptest.usesCase.detail

import com.example.leagueapptest.domaine.Repository.deatil.manager.DetailTeamRepo
import com.example.leagueapptest.domaine.Repository.loadLeague.manager.LoadLeagueRepo
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.detail.model.DetailTeamUiModel
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailUsesCaseImp @Inject constructor(private val loadLeagueRepo: DetailTeamRepo) :
    DetailUsesCase {
    override fun loadTeamById(
        leagueId: Int,
        scope: CoroutineScope
    ): Flow<DataState<DetailTeamUiModel>> = callbackFlow {
        loadLeagueRepo.loadTeamById(leagueId, scope).collect {
            when (it) {
                is DataState.Success -> {
                    send(
                        DataState.Success(
                            DetailTeamUiModel(
                                it.data.teamName,
                                it.data.teamBanner,
                                it.data.teamCountry,
                                it.data.teamDescription
                            )
                        )
                    )
                }
                is DataState.Error -> {
                    send(it)
                }
            }
        }
        awaitClose()
    }
}