package com.sxiaozhi.somking.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.sxiaozhi.somking.core.base.BaseFragment
import com.sxiaozhi.somking.databinding.FragmentToolsBinding
import com.sxiaozhi.somking.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToolsFragment: BaseFragment<FragmentToolsBinding>()  {

    override fun getToolbar(): Toolbar? = null

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentToolsBinding {
        return FragmentToolsBinding.inflate(inflater, container, true)
    }

    override val windowInsetsCompatView: View
        get() = binding.headerLayout

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}