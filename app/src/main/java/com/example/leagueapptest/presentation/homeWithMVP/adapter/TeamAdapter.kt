package com.example.leagueapptest.presentation.homeWithMVP.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leagueapptest.databinding.ItemTeamBinding
import com.example.leagueapptest.usesCase.home.model.TeamUiModel

class TeamAdapter(private val adapterViewInteraction: AdapterViewInteraction) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    private var listTeams: List<TeamUiModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listTeams[position])

    override fun getItemCount(): Int = listTeams.size

    inner class ViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamModel: TeamUiModel) {
            binding.apply {
                Glide.with(this.root.context).load(teamModel.teamLogo).into(teamLogo)
                mainContainer.setOnClickListener {
                    adapterViewInteraction.onItemClicked(teamModel.teamId)
                }
            }

        }
    }

    fun setTeams(listTeams: List<TeamUiModel>) {
        this.listTeams = listTeams
        notifyDataSetChanged()
    }
}