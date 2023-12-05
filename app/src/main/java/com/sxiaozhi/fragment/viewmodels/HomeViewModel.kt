package com.sxiaozhi.fragment.viewmodels

import androidx.lifecycle.*
import com.sxiaozhi.fragment.core.base.BaseViewModel
import com.sxiaozhi.fragment.core.base.DataState
import com.sxiaozhi.fragment.core.extensions.TAG
import com.sxiaozhi.fragment.core.extensions.toConvertData
import com.sxiaozhi.fragment.data.HomeBean
import com.sxiaozhi.fragment.repository.HomeRepository
import com.sxiaozhi.fragment.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    sealed class HomeState : DataState {
        data class HomeDataState(val result: HomeBean?) : HomeState()
    }

    fun getHomeData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = homeRepository.getHome()
            val dataState = response.toConvertData({
                HomeState.HomeDataState(it.data)
            })
            setDataState(dataState)
        } catch (e: Exception) {
            Logger.e(TAG, e.toString())
        }
    }

    fun getSubmitData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = homeRepository.getInitSubmitData()
//            val dataState = response.toConvertData({
//                HomeState.HomeDataState(it.data)
//            })
//            setDataState(dataState)
        } catch (e: Exception) {
            Logger.e(TAG, e.toString())
        }
    }


//    fun submit(request: BlockRequest) = liveData {
//        emit(
//            runCatching {
//                blockRepository.submit(request)
//            }
//        )
//    }
}