package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import com.lolideveloper.shogun.Ui.Model.UserID
import com.lolideveloper.shogun.Utils.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Z1ViewModel @Inject constructor(private val firebaseResponse: FirebaseResponse,mStorage : Storage) : ViewModel() {

    val onResponse = firebaseResponse
    val onStorage = mStorage
    private var _UserID = MutableLiveData<CharSequence>()
    private val _Password = MutableLiveData<CharSequence>()
    val userid: LiveData<CharSequence> = _UserID
    val password: LiveData<CharSequence> = _Password

    fun getUserID(i: CharSequence) {
        _UserID.value = i
    }

    fun getPassword(i: CharSequence) {
        _Password.value = i
    }

    fun onCheckButton(): Boolean {
        val state =
            _UserID.value != null && _UserID.value?.length == 8 && _Password.value != null && _Password.value?.isNotEmpty()!!
        return state
    }
}