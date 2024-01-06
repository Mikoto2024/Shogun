package com.lolideveloper.shogun.Ui.View.Fragment.Activity

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import androidx.fragment.app.Fragment
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.databinding.InfBinding
import com.lolideveloper.shogun.databinding.Z10Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class z0_update(
    context: Context, txtBuild: String, txtVersion: String, txtDate: String, txtData: String
) : LinearLayout(context) {
    private var binding: InfBinding

    val mView = LayoutInflater.from(context).inflate(R.layout.inf, this, true)

    init {
        mView.foregroundGravity = Gravity.CENTER
        binding = InfBinding.bind(mView)
        run(txtBuild, txtVersion, txtDate, txtData)
    }

    private fun run(mBuild: String, mVersion: String, mDate: String, mData: String) {
        binding.txtBuild.text = "Build : $mBuild"
        binding.txtVerison.text = "Version : $mVersion"
        binding.txtDate.text = "Date : $mDate"
        binding.txtData.text = "$mData"
    }
}

class z1_0 : Fragment() {
    private var mbinding: Z10Binding? = null
    private val binding get() = mbinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mbinding = Z10Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listNovedades = arrayListOf(
            "User registration only",
            "Support android 7 - 14",
        )
        val stringBuilder = StringBuilder()
        for ((index, item) in listNovedades.withIndex()) {
            stringBuilder.append("${index + 1}- $item\n")
        }
        addView(requireContext(), "Beta", "0.1", "06/7/2023", stringBuilder.toString())
    }

    private fun addView(
        mthis: Context, txtBuild: String, txtVersion: String, txtDate: String, txtData: String
    ) {
        binding.mroot.addView(
            z0_update(mthis, "$txtBuild", "$txtVersion", "$txtDate", "$txtData")
        )
        binding.mroot.addView(Space(mthis), -1, 14)
    }
}