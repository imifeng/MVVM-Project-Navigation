package com.sxiaozhi.fragment.ui.science

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.sxiaozhi.fragment.core.base.BaseFragment
import com.sxiaozhi.fragment.databinding.FragmentScienceBinding
import com.sxiaozhi.fragment.viewmodels.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScienceFragment: BaseFragment<FragmentScienceBinding>()  {

    override fun getToolbar(): Toolbar? = null

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentScienceBinding {
        return FragmentScienceBinding.inflate(inflater, container, true)
    }

    override val windowInsetsCompatView: View
        get() = binding.headerLayout

    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textDashboard
        calculatorViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}