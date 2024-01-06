package com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip

import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FG1ViewModel @Inject constructor() : ViewModel() {
    /* Esp */
    private val _CheckIsEnabled = MutableLiveData<Boolean>()
    val checkIsEnabled = _CheckIsEnabled

    private val _CheckIsChecked = MutableLiveData<Boolean>()
    val checkIsChecked = _CheckIsChecked

    fun getIsEnabled(i: Boolean) {
        _CheckIsEnabled.value = i
    }

    fun getIsChecked(i: CheckBox) {
        if (!i.isChecked) {
            _CheckIsChecked.value = i.isChecked
        }
    }

    /* Pvp Settings */
    private val _CheckIsEnabled2 = MutableLiveData<Boolean>()
    val checkIsEnabled2 = _CheckIsEnabled2

    private val _CheckIsChecked2 = MutableLiveData<Boolean>()
    val checkIsChecked2 = _CheckIsChecked2
    private val _CheckIsGone = MutableLiveData<Boolean>()
    val checkIsGone = _CheckIsGone
    fun getIsEnabled2(i: Boolean) {
        _CheckIsEnabled2.value = i
    }

    fun getIsChecked2(i: CheckBox) {
        if (!i.isChecked) {
            _CheckIsChecked2.value = i.isChecked
        }
    }

    fun getIsGone(i: CheckBox) {
        _CheckIsGone.value = i.isChecked
    }

    /* Aim Pov */
    private val _CheckIsEnabled3 = MutableLiveData<Boolean>()
    val checkIsEnabled3 = _CheckIsEnabled3

    fun getIsEnabled3(i: Boolean) {
        _CheckIsEnabled3.value = i
    }
}