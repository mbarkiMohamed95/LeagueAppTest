package com.example.leagueapptest.presentation.detailsWithMVVM.actions

sealed class DetailAction{
    data class LoadTeamInfo(val teamId:Int):DetailAction()
}
