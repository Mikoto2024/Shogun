package com.lolideveloper.shogun.Ui.ViewModel

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Activity.z1
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    fun initActivity(mContext: Context) {
        val Bundle =
            ActivityOptionsCompat.makeCustomAnimation(mContext, R.anim.fade_in, R.anim.fade_out)
                .toBundle()
        mContext.startActivity(Intent(mContext, z1::class.java), Bundle)
    }
}