package com.lolideveloper.shogun.Ui.View.Menu.Svip

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.lolideveloper.shogun.databinding.OpBinding

class Fg4_svp(context: Context) : LinearLayout(context) {
    private var _binding: OpBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = OpBinding.inflate(LayoutInflater.from(context),this,true)

    }
}