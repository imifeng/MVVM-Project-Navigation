package com.sxiaozhi.debt.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.sxiaozhi.debt.core.base.BaseFragment
import com.sxiaozhi.debt.databinding.FragmentProfileBinding
import com.sxiaozhi.debt.viewmodels.ProfileViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, true)
    }

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textNotifications
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

}