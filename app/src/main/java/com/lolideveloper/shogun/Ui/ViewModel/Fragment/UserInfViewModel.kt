package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.NetWork.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfViewModel  @Inject constructor(private val firebaseResponse: FirebaseResponse): ViewModel() {
    val onResponse = firebaseResponse
}