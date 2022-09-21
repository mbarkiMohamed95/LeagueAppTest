package com.example.leagueapptest.presentation.homeWithMVP

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.leagueapptest.R
import com.example.leagueapptest.databinding.FragmentHomeBinding
import com.example.leagueapptest.presentation.homeWithMVP.adapter.AdapterViewInteraction
import com.example.leagueapptest.presentation.homeWithMVP.adapter.TeamAdapter
import com.example.leagueapptest.presentation.homeWithMVP.dependencies.HomePresenterDependenciesImp
import com.example.leagueapptest.presentation.homeWithMVP.interaction.HomeFragment
import com.example.leagueapptest.presentation.homeWithMVP.interaction.HomePresenter
import com.example.leagueapptest.tools.dataState.DataState
import com.example.leagueapptest.usesCase.home.model.LeagueUiModel
import com.example.leagueapptest.usesCase.home.model.TeamUiModel
import kotlinx.coroutines.launch


class HomeFragmentImp : Fragment(), HomeFragment, AdapterViewInteraction {
    private var homePresenter: HomePresenter? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter = TeamAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePresenter = HomePresenterImp(HomePresenterDependenciesImp(this))
        lifecycleScope.launch {
            homePresenter?.loadLeagueList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val mView = binding.root
        setUpAdapter()
        return mView
    }

    private fun setUpAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.listItem.layoutManager = layoutManager
        binding.listItem.adapter = adapter
    }

    override fun presentLeagueList(data: DataState<List<LeagueUiModel>>) {
        when (data) {
            is DataState.Success -> {
                var leagueList = data.data.map { it.leagueName }.filter { !it.isNullOrEmpty() }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    leagueList
                )
                binding.searchEdittext.setAdapter(adapter)
                binding.searchEdittext.setOnItemClickListener { adapterView, view, i, l ->
                    Log.i("HomeFragment", "presentLeagueList:${(view as TextView).text} ")
                    var selectedList = (view as TextView).text.toString()
                    getSelectLeagueId(selectedList, data.data) {
                        it?.let {
                            lifecycleScope.launch {
                                homePresenter?.loadTeamByLeagueLeagueList(it)
                            }
                        }
                    }
                }
            }
            is DataState.Error -> {
                Log.i("HomeFragment", "presentLeagueList: null ")
            }
        }
    }

    private fun getSelectLeagueId(
        leagueName: String,
        leagueList: List<LeagueUiModel>,
        callback: (Int?) -> Unit
    ) {
        callback(leagueList.filter { it.leagueName ?: "" == leagueName }[0].LeagueId)
    }

    override fun presentLeagueTeamList(data: DataState<List<TeamUiModel>>) {
        when (data) {
            is DataState.Success -> {
                adapter.setTeams(data.data)
            }
            is DataState.Error -> {

            }
        }
    }

    override fun onItemClicked(teamId: Int) {
        findNavController().navigate(R.id.action_home_to_detailFragment, Bundle().apply {
            putInt("teamId", teamId)
        })
    }

}