package com.lolideveloper.shogun.Ui.View.Menu.Svip

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.lolideveloper.shogun.databinding.VpBinding

class Fg3_svp(context: Context) : LinearLayout(context) {
    private var _binding: VpBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = VpBinding.inflate(LayoutInflater.from(context), this, true)

    }
}