package com.example.leagueapptest.presentation.homeWithMVP.dependencies

import com.example.leagueapptest.presentation.homeWithMVP.interaction.HomeFragment
import com.example.leagueapptest.usesCase.home.HomeUsesCase

interface HomePresenterDependencies {
    val homeFragment: HomeFragment
    val homeUsesCase: HomeUsesCase
}