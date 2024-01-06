package com.lolideveloper.shogun.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DialogPagerAdapter(
    val list: ArrayList<Fragment>,
    fm: FragmentManager,
    lf: Lifecycle
) : FragmentStateAdapter(fm, lf) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}
