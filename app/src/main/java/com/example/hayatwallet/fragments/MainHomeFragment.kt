package com.example.hayatwallet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hayatwallet.R
import com.example.hayatwallet.adapters.CampaignAdapter
import com.example.hayatwallet.adapters.FragmentAdapter
import com.example.hayatwallet.adapters.HomeWalletAdapter
import com.example.hayatwallet.data.HomeWallet
import com.example.hayatwallet.databinding.FragmentMainHomeBinding

class MainHomeFragment : Fragment() {
    
    private lateinit var binding: FragmentMainHomeBinding
    private lateinit var myList: List<HomeWallet>
    private  var homeWalletAdapter = HomeWalletAdapter(arrayListOf())

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

        myList = getHomeWalletList().takeLast(3)
        homeWalletAdapter.updateData(myList)
        binding.recyclerViewHomeWallet.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewHomeWallet.adapter = homeWalletAdapter
        binding.textViewSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_forTabLayoutFragment_to_seeAllSpendFragment2)
        }
    }

    fun getHomeWalletList() : List<HomeWallet>{
        return listOf(
            HomeWallet("apple","25,12",R.drawable.apple,"12.25"),
            HomeWallet("migros","125,12",R.drawable.migros,"14.25"),
            HomeWallet("getir","225,12",R.drawable.getir,"16.00"),
            HomeWallet("starbucks","25,12",R.drawable.starbucks,"12.25"),
            HomeWallet("thy","125,12",R.drawable.thy,"14.25"),
            HomeWallet("pegasus","225,12",R.drawable.pegasus,"16.00"),
            HomeWallet("bim","25,12",R.drawable.bim,"12.25"),
            HomeWallet("hayat finans","125,12",R.drawable.img_welcome,"14.25"),
            HomeWallet("samsung","225,12",R.drawable.samsung,"16.00"),
            HomeWallet("nescafe","125,12",R.drawable.nescafe,"14.25")
        )
    }

}

