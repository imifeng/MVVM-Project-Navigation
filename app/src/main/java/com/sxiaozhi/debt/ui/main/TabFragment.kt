package com.sxiaozhi.debt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sxiaozhi.debt.R
import com.sxiaozhi.debt.core.base.BaseFragment
import com.sxiaozhi.debt.databinding.FragmentTabBinding
import com.sxiaozhi.debt.utils.Logger
import com.sxiaozhi.debt.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : BaseFragment<FragmentTabBinding>() {

    override fun getToolbar() = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup): FragmentTabBinding {
        return FragmentTabBinding.inflate(inflater, container, true)
    }

    private val homeViewModel: HomeViewModel by viewModels()

    private val navHostFragment: NavHostFragment
        get() {
            return childFragmentManager
                .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController()
        navView.setupWithNavController(navController)

        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    onNavDestinationSelected(item, navHostFragment.navController)
                    false
                }
                R.id.navigation_dashboard -> {
                    onNavDestinationSelected(item, navHostFragment.navController)
                    false
                }
                R.id.navigation_calculator -> {
                    onNavDestinationSelected(item, navHostFragment.navController)
//                    val feedFragment = feedFragment
//                    if (feedFragment == null &&
//                        navHostFragment.navController.currentDestination?.hierarchy?.any { it.id == item.itemId } == true
//                    ) {
//                        activity?.onBackPressed()
//                        true
//                    } else {
//                        onNavDestinationSelected(item, navHostFragment.navController)
//                    }
                    false
                }
                R.id.navigation_profile -> {
                    onNavDestinationSelected(item, navHostFragment.navController)
                    false
                }
                else -> {
                    onNavDestinationSelected(item, navHostFragment.navController)
                }
            }
        }
    }

    private fun onNavDestinationSelected(
        item: MenuItem,
        navController: NavController,
    ): Boolean {
        try {
            return NavigationUI.onNavDestinationSelected(item, navController)
        } catch (e: Exception) {
            Logger.d(TAG, e.toString())
        }
        return false
    }

}