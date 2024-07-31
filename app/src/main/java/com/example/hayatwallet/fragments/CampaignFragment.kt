/*
package com.example.hayatwallet.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.hayatwallet.adapters.CampaignAdapter
import com.example.hayatwallet.data.HayatCampaign
import com.example.hayatwallet.databinding.FragmentCampaignBinding
import com.example.hayatwallet.viewModels.CampaignViewModel
import org.jsoup.Jsoup

class CampaignFragment : Fragment() {
    private lateinit var binding: FragmentCampaignBinding
    private val viewModel: CampaignViewModel by viewModels()
    private val campaignAdapter = CampaignAdapter(arrayListOf())
    private var campaignList: MutableList<HayatCampaign>? = null
    var campaigns = mutableListOf<HayatCampaign>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCampaignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        Thread {
            viewModel.getData()
        }.start()

        handler.post {
            viewModel.campaigns.observe(viewLifecycleOwner) {
                    result -> campaignList?.add(result!!)
                println(campaignList)
            }
        }

    }

}*/
package com.example.hayatwallet.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hayatwallet.adapters.CampaignAdapter
import com.example.hayatwallet.data.HayatCampaign
import com.example.hayatwallet.databinding.FragmentCampaignBinding
import com.example.hayatwallet.viewModels.CampaignViewModel

class CampaignFragment : Fragment() {
    private lateinit var binding: FragmentCampaignBinding
    private val viewModel: CampaignViewModel by viewModels()
    private val campaignAdapter = CampaignAdapter(arrayListOf())
    private var campaignList: MutableList<HayatCampaign>? = null
    private lateinit var myList: List<HayatCampaign>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCampaignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewOffers.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewOffers.adapter = campaignAdapter

        val handler = Handler(Looper.getMainLooper())
        Thread {
            viewModel.getData()
        }.start()

        handler.post {
            viewModel.campaigns.observe(viewLifecycleOwner) {
                    result -> myList = result
                campaignAdapter.updateData(myList)
                binding.recyclerViewOffers.layoutManager = LinearLayoutManager(requireActivity())
                binding.recyclerViewOffers.adapter = campaignAdapter
            }
        }
    }
}
