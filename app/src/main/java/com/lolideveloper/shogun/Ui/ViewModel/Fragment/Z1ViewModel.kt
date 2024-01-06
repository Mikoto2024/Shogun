package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import com.lolideveloper.shogun.Utils.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Z1ViewModel @Inject constructor(
    private val firebaseResponse: FirebaseResponse,
    mStorage: Storage
) : ViewModel() {
    val onResponse = firebaseResponse
    val onStorage = mStorage

    private var _userid = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isEnabled = MutableLiveData<Boolean>()

    val userid = _userid
    val password = _password
    val isEnabled = _isEnabled


    fun getLogin(user: String, pass: String) {
        _userid.value = user
        _password.value = pass
        _isEnabled.value = isValidUserID() && isValidPassword()
    }

    private fun isValidUserID(): Boolean {
        return userid.value?.length == 8
    }

    private fun isValidPassword(): Boolean {
        return password.value?.isNotEmpty()!!
    }

}