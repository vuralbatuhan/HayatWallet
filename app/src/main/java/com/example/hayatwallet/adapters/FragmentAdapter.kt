package com.example.hayatwallet.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hayatwallet.fragments.MainHomeFragment
import com.example.hayatwallet.fragments.OffersFragment
import com.example.hayatwallet.fragments.ProfileFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            MainHomeFragment()
        } else
            return if (position == 1) {
            OffersFragment()
        } else
            return if(position == 2) {
            ProfileFragment()
        } else {
            return MainHomeFragment()
            }

    }
}