package com.example.hayatwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hayatwallet.data.HayatCampaign
import com.example.hayatwallet.databinding.ItemRowCampaignBinding
import com.example.hayatwallet.fragments.ForTabLayoutFragmentDirections

class CampaignAdapter(private val campaigns: MutableList<HayatCampaign>) :
    RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    inner class CampaignViewHolder(private val binding: ItemRowCampaignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(campaign: HayatCampaign) {
            binding.textViewCampaign.text = campaign.title
            Glide.with(binding.imageViewCampaign.context).load(campaign.imageUrl).into(binding.imageViewCampaign)

        }

        fun goDetail(campaign: HayatCampaign) {
            binding.buttonCampaign.setOnClickListener {
                val action = ForTabLayoutFragmentDirections.actionForTabLayoutFragmentToCampaignDetailFragment(campaign)
                it.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = ItemRowCampaignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CampaignViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {

        holder.bind(campaigns[position])
        holder.goDetail(campaigns[position])
    }

    override fun getItemCount(): Int = campaigns.size

    fun updateData(newCampaigns: List<HayatCampaign>) {
        campaigns.clear()
        campaigns.addAll(newCampaigns)
        notifyDataSetChanged()
    }


}
