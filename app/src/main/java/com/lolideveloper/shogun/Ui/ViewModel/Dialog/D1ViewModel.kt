package com.lolideveloper.shogun.Ui.ViewModel.Dialog

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class D1ViewModel @Inject constructor(private val firebaseResponse: FirebaseResponse) : ViewModel() {
    private val _UserID = MutableLiveData<CharSequence>()
    private val _Password = MutableLiveData<CharSequence>()
    private val _ConfirmPassword = MutableLiveData<CharSequence>()
    val userid = _UserID
    val password = _Password
    val confirmpassword = _ConfirmPassword

    fun getUserID(i: CharSequence) {
        _UserID.value = i
    }

    fun getPassword(i: CharSequence) {
        _Password.value = i
    }

    fun getConfirmPassword(i: CharSequence) {
        _ConfirmPassword.value = i
    }

    fun onCheckButton(btn: Button) {
        val state = _UserID.value?.length == 8 &&
                _Password.value?.isNotEmpty() == true &&
                _ConfirmPassword.value == _Password.value
        btn.isEnabled = state
    }
    fun onResponse() : FirebaseResponse{
        return firebaseResponse
    }
}