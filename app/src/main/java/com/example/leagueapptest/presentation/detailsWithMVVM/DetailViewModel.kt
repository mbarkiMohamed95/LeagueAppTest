package com.example.leagueapptest.presentation.detailsWithMVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueapptest.presentation.detailsWithMVVM.actions.DetailAction
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.detail.DetailUsesCaseImp
import com.example.leagueapptest.usesCase.detail.model.DetailTeamUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailUsesCaseImp: DetailUsesCaseImp) :
    ViewModel() {

    private val _dataState: MutableLiveData<DataState<DetailTeamUiModel>> =
        MutableLiveData(
            DataState.Idle
        )

    val dataState: LiveData<DataState<DetailTeamUiModel>> get() = _dataState

    fun handleAction(intent: DetailAction) {
        when (intent) {
            is DetailAction.LoadTeamInfo -> {
                loadDetailTeam(intent.teamId)
            }
        }
    }

   private fun loadDetailTeam(teamId: Int) {
        viewModelScope.launch {
            detailUsesCaseImp.loadTeamById(teamId, this).collect {
                _dataState.value = it
            }
        }
    }
}