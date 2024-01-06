package com.lolideveloper.shogun.Ui.View.Dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentActivity
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.ViewModel.Dialog.D1ViewModel
import com.lolideveloper.shogun.Utils.Util.Companion.copy
import com.lolideveloper.shogun.Utils.Util.Companion.mToast
import com.lolideveloper.shogun.databinding.D1Binding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class D1 @Inject constructor(
    private val context: FragmentActivity,
    private val layout: ConstraintLayout,
) : Dialog(context, R.style.AlertDialogCustom) {
    private var _binding: D1Binding? = null
    private val binding get() = _binding!!

    private val viewModel: D1ViewModel by context.viewModels()

    override fun onStart() {
        super.onStart()
        layout.isGone = true
        window?.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = D1Binding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.edtUserIDrg.doAfterTextChanged { viewModel.getUserID(it.toString()) }
        binding.edtPasswordrg.doAfterTextChanged { viewModel.getPassword(it.toString()) }
        binding.edtConfirmrg.doAfterTextChanged { viewModel.getConfirmPassword(it.toString()) }
        binding.genID.setOnClickListener { binding.txtid1.setText(getIDs()) }
        binding.txtid1.setOnClickListener { copy(context,binding.txtid1.text.toString()) }
        binding.rg.setOnClickListener {
            if (binding.txtid1.text.toString() == binding.edtUserIDrg.text.toString()) {
                viewModel.onResponse().initRegister(
                    context,
                    binding.edtUserIDrg.text.toString(),
                    binding.edtPasswordrg.text.toString()
                )
            } else {
                mToast(context, 0, "Invalid UserID")
            }
        }
    }

    private fun initObservers() {
        viewModel.onCheckButton(binding.rg)
        viewModel.userid.observe(context) {
            viewModel.onCheckButton(binding.rg)
        }
        viewModel.password.observe(context) {
            viewModel.onCheckButton(binding.rg)
        }
        viewModel.confirmpassword.observe(context) {
            viewModel.onCheckButton(binding.rg)
        }
    }

    private fun getIDs(): String {
        val num = "876962345"
        val sb = java.lang.StringBuilder(8)
        for (x in 0 until 8) {
            val random = (num.indices).random()
            sb.append(num[random])
        }
        sb.insert((0 until 8).random(), "")
        return sb.toString()
    }

    override fun dismiss() {
        super.dismiss()
        layout.isGone = false
        setOnDismissListener(null)
    }
}