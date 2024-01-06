package com.lolideveloper.shogun.Ui.ViewModel.Dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import com.lolideveloper.shogun.Utils.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class D2ViewModel @Inject constructor(private val firebaseResponse: FirebaseResponse, private val mStorage: Storage) : ViewModel() {
    private val _State = MutableLiveData<Int>()
    val state = _State

    init {
        _State.value = mStorage.getPager().toInt()
    }

    fun svState(ps: Int) {
        mStorage.svPager(ps.toString())
    }

    fun getState(): Int {
        return _State.value?.toInt()!!
    }


    fun onResponse() : FirebaseResponse {
        return firebaseResponse
    }
}