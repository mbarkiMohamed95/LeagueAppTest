package com.example.leagueapptest.presentation.homeWithMVP.interaction

interface HomePresenter {
    suspend fun loadLeagueList()
    suspend fun loadTeamByLeagueLeagueList(leagueId:Int)
}