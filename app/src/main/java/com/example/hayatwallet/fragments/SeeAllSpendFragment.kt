package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hayatwallet.R
import com.example.hayatwallet.adapters.HomeWalletAdapter
import com.example.hayatwallet.data.HomeWallet
import com.example.hayatwallet.databinding.FragmentSeeAllSpendBinding

class SeeAllSpendFragment : Fragment() {
    private lateinit var binding: FragmentSeeAllSpendBinding
    private lateinit var myList: List<HomeWallet>
    private var homeWalletAdapter = HomeWalletAdapter(arrayListOf())
    private var mainHomeFragment = MainHomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAllSpendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myList = mainHomeFragment.getHomeWalletList()
        homeWalletAdapter.updateData(myList)
        binding.recyclerViewSeeAll.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewSeeAll.adapter = homeWalletAdapter
    }

}