package com.example.leagueapptest.di


import com.example.leagueapptest.domaine.Network.detailTeam.DetailTeamManager
import com.example.leagueapptest.domaine.Network.detailTeam.DetailTeamNetworkManagerImp
import com.example.leagueapptest.domaine.Repository.deatil.manager.DetailTeamRepo
import com.example.leagueapptest.domaine.Repository.deatil.manager.DetailTeamRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomaineModule {

    @Binds
    abstract fun provideDetailTeamNetworkManagerImp(detailTeamNetworkManagerImp: DetailTeamNetworkManagerImp): DetailTeamManager


    @Binds
    abstract fun provideDetailTeamRepoImp(detailTeamRepoImp: DetailTeamRepoImp): DetailTeamRepo


}