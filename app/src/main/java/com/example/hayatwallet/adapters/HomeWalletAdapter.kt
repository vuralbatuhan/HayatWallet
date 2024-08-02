package com.example.hayatwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hayatwallet.data.HomeWallet
import com.example.hayatwallet.databinding.FragmentMainHomeBinding
import com.example.hayatwallet.databinding.FragmentSeeAllSpendBinding
import com.example.hayatwallet.databinding.SpecialLayoutHomeWalletBinding
import com.example.hayatwallet.fragments.ForTabLayoutFragmentDirections

class HomeWalletAdapter (private var homeWallet: List<HomeWallet>) :
    RecyclerView.Adapter<HomeWalletAdapter.CampaignViewHolder>() {

    inner class CampaignViewHolder(private val binding: SpecialLayoutHomeWalletBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeWallet: HomeWallet) {
            binding.textSpecialHomeMoney.text = homeWallet.money
            binding.textSpecialHomeMarka.text = homeWallet.marka
            binding.textSpecialHometime.text = homeWallet.time
            Glide.with(binding.imageSpecialHomeWallet).load(homeWallet.markasImage).into(binding.imageSpecialHomeWallet)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = SpecialLayoutHomeWalletBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
       holder.bind(homeWallet[position])

    }

    override fun getItemCount(): Int = homeWallet.size

    fun updateData(newCampaigns: List<HomeWallet>) {
        homeWallet = newCampaigns
        notifyDataSetChanged()
    }

}
