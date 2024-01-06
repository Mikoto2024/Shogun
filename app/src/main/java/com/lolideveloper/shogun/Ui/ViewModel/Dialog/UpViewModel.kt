package com.lolideveloper.shogun.Ui.ViewModel.Dialog

import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpViewModel @Inject constructor(private val firebaseResponse : FirebaseResponse)  : ViewModel() {
    fun onResponse() : FirebaseResponse{
        return firebaseResponse
    }
}