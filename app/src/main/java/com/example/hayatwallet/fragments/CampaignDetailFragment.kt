package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hayatwallet.R
import com.example.hayatwallet.databinding.FragmentCampaignDetailBinding

class CampaignDetailFragment : Fragment() {
    private lateinit var binding: FragmentCampaignDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCampaignDetailBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: CampaignDetailFragmentArgs by navArgs()
        val campaign = bundle.hayatCampaign

        binding.textViewCampaignDetail.text = campaign.title
        Glide.with(requireActivity()).load(campaign.imageUrl).into(binding.imageViewCampaignDetail)
    }

}