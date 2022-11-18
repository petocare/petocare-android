package com.petocare.ui.onBoarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petocare.infra.base_class.BaseViewModel

class OnBoardingViewModel: BaseViewModel() {
    private val dataResult: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<String>> {
        return dataResult
    }

    private fun loadUsers() {
//        CompositeDisposable().add(
//
//        )
    }
}