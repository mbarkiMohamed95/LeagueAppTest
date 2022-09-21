package com.example.leagueapptest.di


import com.example.leagueapptest.usesCase.detail.DetailUsesCase
import com.example.leagueapptest.usesCase.detail.DetailUsesCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UsesCaseModule {
    @Binds
    abstract fun provideDetailUsesCaseImp(detailUsesCaseImp: DetailUsesCaseImp): DetailUsesCase
}