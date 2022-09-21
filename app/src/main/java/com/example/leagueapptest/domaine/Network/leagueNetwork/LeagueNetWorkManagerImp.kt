package com.example.leagueapptest.domaine.Network.leagueNetwork

import com.example.leagueapptest.BuildConfig
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.league.LeagueNetWorkModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.league.LeagueTeamResponseModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.ResponseTeamModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.Teams
import com.example.leagueapptest.domaine.Network.service.ApiServices
import com.example.leagueapptest.domaine.Network.service.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueNetWorkManagerImp constructor(private val apiServices: ApiServices) :
    LeagueNetWorkManager {
    override fun loadLeagueList(): Flow<List<LeagueNetWorkModel>?> = callbackFlow {
        apiServices.loadLeagueList(BuildConfig.apikey)
            .enqueue(object : Callback<LeagueTeamResponseModel> {
                override fun onResponse(
                    call: Call<LeagueTeamResponseModel>,
                    response: Response<LeagueTeamResponseModel>
                ) {
                    CoroutineScope(Dispatchers.Default).launch {
                        send(response.body()?.leagues)
                    }
                }

                override fun onFailure(call: Call<LeagueTeamResponseModel>, t: Throwable) {
                    CoroutineScope(Dispatchers.Default).launch {
                        send(null)
                    }

                }

            })
        awaitClose()
    }

    override fun loadLeagueTeamList(leagueId: Int): Flow<List<Teams>?> = callbackFlow {
        apiServices.loadLeagueTeamList(BuildConfig.apikey, leagueId)
            .enqueue(object : Callback<ResponseTeamModel> {
                override fun onResponse(
                    call: Call<ResponseTeamModel>,
                    response: Response<ResponseTeamModel>
                ) {
                    CoroutineScope(Dispatchers.Default).launch {
                        send(response.body()?.teams)
                    }
                }

                override fun onFailure(call: Call<ResponseTeamModel>, t: Throwable) {
                    CoroutineScope(Dispatchers.Default).launch {
                        send(null)
                    }
                }
            })
        awaitClose()
    }

    companion object {
        private val leagueNetWorkManager: LeagueNetWorkManagerImp by lazy {
            LeagueNetWorkManagerImp(
                NetworkHelper.getApi(
                    ApiServices::class.java,
                    BuildConfig.baseUrl
                )
            )
        }

        fun getLeagueNetWorkManagerInstance(): LeagueNetWorkManager = leagueNetWorkManager
    }
}