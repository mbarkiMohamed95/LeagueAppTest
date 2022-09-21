package com.example.leagueapptest.presentation.homeWithMVP

import com.example.leagueapptest.presentation.homeWithMVP.dependencies.HomePresenterDependencies
import com.example.leagueapptest.presentation.homeWithMVP.interaction.HomePresenter

class HomePresenterImp constructor(
    private val homePresenterDependencies: HomePresenterDependencies
) : HomePresenter {

    override suspend fun loadLeagueList() {
        homePresenterDependencies.homeUsesCase.loadLeagueList().collect{
            homePresenterDependencies.homeFragment.presentLeagueList(it)
        }
    }

    override suspend fun loadTeamByLeagueLeagueList(leagueId: Int) {
       homePresenterDependencies.homeUsesCase.loadLeagueTeamList(leagueId).collect{
           homePresenterDependencies.homeFragment.presentLeagueTeamList(it)
       }
    }
}