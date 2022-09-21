package com.example.leagueapptest.domaine.Network.detailTeam


import com.example.leagueapptest.BuildConfig
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.ResponseTeamModel
import com.example.leagueapptest.domaine.Network.leagueNetwork.model.team.Teams
import com.example.leagueapptest.domaine.Network.service.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailTeamNetworkManagerImp @Inject constructor(private val apiServices: ApiServices) :
    DetailTeamManager {
    override fun loadTeamById(teamId: Int, scope: CoroutineScope): Flow<List<Teams>?> = callbackFlow {
        apiServices.loadTeamById(BuildConfig.apikey, teamId).enqueue(object :
            Callback<ResponseTeamModel> {
            override fun onResponse(
                call: Call<ResponseTeamModel>,
                response: Response<ResponseTeamModel>
            ) {
                scope.launch {
                    send(response.body()?.teams)
                }
            }

            override fun onFailure(call: Call<ResponseTeamModel>, t: Throwable) {
                scope.launch {
                    send(null)
                }
            }
        })
        awaitClose ()
    }

}