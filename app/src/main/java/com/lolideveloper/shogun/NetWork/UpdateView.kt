package com.lolideveloper.shogun.NetWork

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.ViewModel.Dialog.UpViewModel
import com.lolideveloper.shogun.databinding.UpBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateView @Inject constructor(
    private val context: FragmentActivity,
    private val layout: ConstraintLayout
) : Dialog(context, R.style.AlertDialogCustom) {
    private var _binding: UpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpViewModel by context.viewModels()

    override fun onStart() {
        super.onStart()
        this.setCancelable(false)
        layout.isGone = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = UpBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.dw.setOnClickListener { viewModel.onResponse().initUpdate(context) }
    }

    override fun dismiss() {
        super.dismiss()
        layout.isGone = false
        setOnDismissListener(null)
    }
}
