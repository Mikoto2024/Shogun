package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Z2ViewModel @Inject constructor(private val firebaseResponse: FirebaseResponse) : ViewModel() {
    val onResponse = firebaseResponse
    private val _Code = MutableLiveData<String>()
    val code = _Code
    fun getCode(i: String) {
        _Code.value = i
    }

    fun onCheckButton(): Boolean {
        val state = _Code.value != null && _Code.value?.length!! == 15
        return state
    }
}