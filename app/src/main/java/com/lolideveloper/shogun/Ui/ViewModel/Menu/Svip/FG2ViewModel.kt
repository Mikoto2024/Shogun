package com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip

import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FG2ViewModel @Inject constructor() : ViewModel() {
    private val _isChecked = MutableLiveData<Boolean>()
    private val _isChecked2 = MutableLiveData<Boolean>()
    val isChecked = _isChecked
    val isChecked2 = _isChecked2

    init {
        _isChecked.value = false
        _isChecked2.value = false
    }

    fun getIsChecked(a: Boolean) {
        _isChecked.value = a
    }

    fun getIsChecked2(b: Boolean) {
        _isChecked2.value = b
    }

    fun getStates(a: LinearLayout, b: LinearLayout) {
        a.isGone = isChecked.value != true
        b.isGone = isChecked2.value != true
    }
}