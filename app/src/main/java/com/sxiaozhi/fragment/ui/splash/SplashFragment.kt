package com.sxiaozhi.fragment.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sxiaozhi.fragment.R
import com.sxiaozhi.fragment.core.base.BaseFragment
import com.sxiaozhi.fragment.core.extensions.navigateWithCatch
import com.sxiaozhi.fragment.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getToolbar() = null

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startApp(2000)
    }

    private fun startApp(delay: Long = 0) {
        lifecycleScope.launch {
            delay(delay)
            findNavController().navigateWithCatch(R.id.action_splashFragment_to_tabFragment)
        }
    }
}