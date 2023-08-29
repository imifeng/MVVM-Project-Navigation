package com.sxiaozhi.debt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.sxiaozhi.debt.core.base.BaseFragment
import com.sxiaozhi.debt.databinding.FragmentHomeBinding
import com.sxiaozhi.debt.viewmodels.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getToolbar(): Toolbar = binding.toolbar

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, true)
    }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

}