package com.example.hayatwallet.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hayatwallet.R
import com.example.hayatwallet.adapters.CampaignAdapter
import com.example.hayatwallet.adapters.FragmentAdapter
import com.example.hayatwallet.adapters.HomeCampaignAdapter
import com.example.hayatwallet.adapters.HomeWalletAdapter
import com.example.hayatwallet.data.HayatCampaign
import com.example.hayatwallet.data.HomeWallet
import com.example.hayatwallet.databinding.FragmentMainHomeBinding
import com.example.hayatwallet.viewModels.CampaignViewModel

class MainHomeFragment : Fragment() {

    private lateinit var binding: FragmentMainHomeBinding
    private lateinit var homePageList: List<HomeWallet>
    private lateinit var homePageCampaignList: List<HayatCampaign>
    private  var homeWalletAdapter = HomeWalletAdapter(arrayListOf())
    private val viewModel: CampaignViewModel by viewModels()
    private val homeCampaignAdapter = HomeCampaignAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePageList = getHomeWalletList().takeLast(3)
        homeWalletAdapter.updateData(homePageList)
        binding.recyclerViewHomeWallet.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewHomeWallet.adapter = homeWalletAdapter
        binding.textViewSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_forTabLayoutFragment_to_seeAllSpendFragment2)
        }
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewHomeWallet.context,
            (binding.recyclerViewHomeWallet.layoutManager as LinearLayoutManager).orientation
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_row))
        binding.recyclerViewHomeWallet.addItemDecoration(dividerItemDecoration)

        binding.recyclerViewHorizontalCampaign.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewHorizontalCampaign.adapter = homeCampaignAdapter
        val handler = Handler(Looper.getMainLooper())
        Thread {
            viewModel.getData()
        }.start()

        handler.post {
            viewModel.campaigns.observe(viewLifecycleOwner) {
                    result -> homePageCampaignList = result
                homeCampaignAdapter.updateData(homePageCampaignList)
                binding.recyclerViewHorizontalCampaign.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerViewHorizontalCampaign.adapter = homeCampaignAdapter
            }
        }
    }

    fun getHomeWalletList() : List<HomeWallet>{
        return listOf(
            HomeWallet("Apple","-₺ 25,12",R.drawable.apple,"12.25"),
            HomeWallet("Migros","-₺ 125,12",R.drawable.migros,"14.25"),
            HomeWallet("Getir","-₺ 225,12",R.drawable.getir,"16.00"),
            HomeWallet("Thy","-₺ 125,12",R.drawable.thy,"14.25"),
            HomeWallet("Pegasus","-₺ 225,12",R.drawable.pegasus,"16.00"),
            HomeWallet("Hayat Finans","-₺ 125,12",R.drawable.img_welcome,"14.25"),
            HomeWallet("Starbucks","-₺ 25,12",R.drawable.starbucks,"12.25"),
            HomeWallet("Bim","-₺ 452,83",R.drawable.bim,"12.25"),
            HomeWallet("Samsung","-₺ 3999,99",R.drawable.samsung,"16.00"),
            HomeWallet("Nescafe","-₺ 25,00",R.drawable.nescafe,"14.30")
        )
    }

}

