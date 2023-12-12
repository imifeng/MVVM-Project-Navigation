package com.sxiaozhi.fragment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sxiaozhi.fragment.R
import com.sxiaozhi.fragment.core.base.BaseFragment
import com.sxiaozhi.fragment.core.constant.Constant.tabNavigationList
import com.sxiaozhi.fragment.databinding.FragmentTabBinding
import com.sxiaozhi.fragment.utils.Logger
import com.sxiaozhi.fragment.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : BaseFragment<FragmentTabBinding>(), NavController.OnDestinationChangedListener {

    override fun getToolbar() = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup): FragmentTabBinding {
        return FragmentTabBinding.inflate(inflater, container, true)
    }

    private val navHostFragment: NavHostFragment
        get() {
            return childFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navView.setupWithNavController(navHostFragment.navController)
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
//                    onNavDestinationSelected(item, navHostFragment.navController)
                }
                R.id.navigation_tools -> {
//                    onNavDestinationSelected(item, navHostFragment.navController)
                }
                R.id.navigation_find -> {
//                    onNavDestinationSelected(item, navHostFragment.navController)
                }
                R.id.navigation_profile -> {
//                    onNavDestinationSelected(item, navHostFragment.navController)
                }
                else -> {
//                    onNavDestinationSelected(item, navHostFragment.navController)
                }
            }
            onNavDestinationSelected(item, navHostFragment.navController)
        }
        navHostFragment.navController.addOnDestinationChangedListener(this)
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

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Logger.d(TAG, "onDestinationChanged $destination")
        Logger.d(TAG, "onDestinationChanged $destination")
        if (tabNavigationList.contains(destination.id)) {
            Logger.d(TAG,"Display bottom view")
            // Do nothing
            toggleBottomNavView(true)
        } else {
            Logger.d(TAG,"Hide bottom view")
            toggleBottomNavView(false)
        }
    }

    private fun toggleBottomNavView(enable: Boolean) {
        binding.navView.isVisible = enable
    }

}