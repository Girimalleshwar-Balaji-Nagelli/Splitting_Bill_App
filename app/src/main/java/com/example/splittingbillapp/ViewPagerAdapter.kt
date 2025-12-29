package com.example.splittingbillapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.splittingbillapp.Fragments.SplitByAmountFragment
import com.example.splittingbillapp.Fragments.SplitByItemFragment
import com.example.splittingbillapp.Fragments.SplitByPercentageFragment
import com.example.splittingbillapp.Fragments.SplitBySharesFragment
import com.example.splittingbillapp.Fragments.SplitEvenlyFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(SplitEvenlyFragment(),SplitByItemFragment() ,SplitByAmountFragment(),SplitBySharesFragment(),SplitByPercentageFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}