package com.example.leagueapptest.presentation.detailsWithMVVM

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.leagueapptest.databinding.FragmentDetailBinding
import com.example.leagueapptest.presentation.detailsWithMVVM.actions.DetailAction
import com.example.leagueapptest.tools.dataState.DataState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var teamId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamId = arguments?.getInt("teamId")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val mView = binding.root
        teamId?.let {
            viewModel.handleAction(DetailAction.LoadTeamInfo(it))
        }

        observeResponse()
        return mView
    }

    private fun observeResponse() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    binding.apply {
                        Glide.with(requireContext()).load(it.data.teamBanner ?: "")
                            .into(bannerImage)
                        it.data.teamName?.let {
                            teamName.text = it
                        }
                        it.data.teamCountry?.let {
                            teamCountry.text = it
                        }
                        it.data.teamDescription?.let {
                            description.text = it
                        }

                    }
                }
                is DataState.Error -> {

                }
            }
        }
    }

}