package com.example.leagueapptest.presentation.homeWithMVP.dependencies

import com.example.leagueapptest.domaine.Repository.loadLeague.manager.LoadLeagueRepoImp.Companion.getLoadLeagueRepoImp
import com.example.leagueapptest.presentation.homeWithMVP.interaction.HomeFragment
import com.example.leagueapptest.usesCase.home.HomeUsesCase
import com.example.leagueapptest.usesCase.home.HomeUsesCaseImp

class HomePresenterDependenciesImp(
    override val homeFragment: HomeFragment
) : HomePresenterDependencies {
    override val homeUsesCase: HomeUsesCase=HomeUsesCaseImp(getLoadLeagueRepoImp())
}