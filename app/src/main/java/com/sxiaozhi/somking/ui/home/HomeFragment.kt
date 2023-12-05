package com.sxiaozhi.somking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sxiaozhi.somking.R
import com.sxiaozhi.somking.core.base.BaseFragment
import com.sxiaozhi.somking.core.base.ErrorState
import com.sxiaozhi.somking.core.base.LoginState
import com.sxiaozhi.somking.core.base.adapter.VerticalSpaceItemDecoration
import com.sxiaozhi.somking.core.extensions.makeShortToast
import com.sxiaozhi.somking.core.extensions.navigateWithCatch
import com.sxiaozhi.somking.data.AgentBean
import com.sxiaozhi.somking.data.CaseBean
import com.sxiaozhi.somking.databinding.FragmentHomeBinding
import com.sxiaozhi.somking.ui.home.adapter.AgentAdapter
import com.sxiaozhi.somking.ui.home.adapter.HomeAdapter
import com.sxiaozhi.somking.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getToolbar(): Toolbar? = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, true)
    }

    private val homeViewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(callback = object : HomeAdapter.Callback{
            override fun onClickItem(item: CaseBean) {
//                TODO("Not yet implemented")
            }

            override fun onClickAsk(item: CaseBean) {
//                TODO("Not yet implemented")
            }

        })
    }

    private val agentAdapter: AgentAdapter by lazy {
        AgentAdapter(callback = object : AgentAdapter.Callback{
            override fun onClickAsk(item: AgentBean) {
//                TODO("Not yet implemented")
            }
        })
    }

    private val mergeAdapter by lazy {
        ConcatAdapter(
            agentAdapter,
            homeAdapter
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_12))
            )
            adapter = mergeAdapter
        }

        homeViewModel.dataState.observe(viewLifecycleOwner){ state ->
            when (state) {
                is HomeViewModel.HomeState.HomeDataState -> {
                    val result = state.result
                    result?.agent?.let {
                        agentAdapter.setRecyclerData(listOf(it))
                    }
                    result?.cases?.let {
                        homeAdapter.setRecyclerData(it)
                    }
                }

//                is LoadingState -> startProgressBar()

                is LoginState -> {
                    gotoLoginAuth()
                }

                is ErrorState -> {
                    state.message?.let {
                        context?.makeShortToast(it)
                    }
                }
            }
        }

        binding.btnSolution.setOnClickListener {
//            checkLoginAuth {
                findNavController().navigateWithCatch(R.id.action_HomeFragment_to_DebtAskFragment)
//            }
        }

        homeViewModel.getHomeData()
    }

}