package com.example.leagueapptest.domaine.Repository.deatil.manager

import com.example.leagueapptest.domaine.Network.detailTeam.DetailTeamManager
import com.example.leagueapptest.domaine.Repository.deatil.model.DetailTeamRepoModel
import com.example.leagueapptest.tools.dataState.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailTeamRepoImp @Inject constructor(private val detailTeamManager: DetailTeamManager) :
    DetailTeamRepo {

    override fun loadTeamById(
        teamId: Int,
        scope: CoroutineScope
    ): Flow<DataState<DetailTeamRepoModel>> = callbackFlow {
        detailTeamManager.loadTeamById(teamId, scope).collect {
            if (!it.isNullOrEmpty()) {
                var res = it[0]

                send(
                    DataState.Success(
                        DetailTeamRepoModel(
                            res.strTeam,
                            res.strTeamBanner,
                            res.strCountry,
                            res.strDescriptionEN
                        )
                    )
                )
            } else {
                send(DataState.Error(Exception("null")))
            }
        }
        awaitClose()
    }

}