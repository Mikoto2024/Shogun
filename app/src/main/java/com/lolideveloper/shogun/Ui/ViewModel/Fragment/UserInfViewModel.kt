package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfViewModel  @Inject constructor(private val firebaseResponse: FirebaseResponse): ViewModel() {

    private val _code  = MutableLiveData<CharSequence>()
    val code = _code
    fun onResponse() : FirebaseResponse{
        return firebaseResponse
    }
    fun getCharSequence(i : CharSequence){
        _code.value = i
    }


    fun checkButtonState() : Boolean{
        return _code.value != null && _code.value!!.length == 15
    }
}