package com.lolideveloper.shogun.Ui.View.Menu.Svip

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.lolideveloper.shogun.Core.LifecycleOwnerVirtual
import com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip.FG2ViewModel
import com.lolideveloper.shogun.databinding.K0Binding
import javax.inject.Inject

class Fg2_svp @Inject constructor(
    private val context: Context,
    private val viewModel: FG2ViewModel
) : LinearLayout(context) {
    private var _binding: K0Binding? = null
    private val binding get() = _binding!!
    private val lifecycleRegistry = LifecycleOwnerVirtual()

    init {
        _binding = K0Binding.inflate(LayoutInflater.from(context), this, true)
        lifecycleRegistry.onStart()
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.eS.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsChecked(isChecked)
        }
        binding.eD.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsChecked2(isChecked)
        }
    }

    private fun initObservers() {
        viewModel.isChecked.observe(lifecycleRegistry) {
            if (it == true) {
                binding.eD.isChecked = false
            }
            viewModel.getStates(binding.kst, binding.kdns)
        }
        viewModel.isChecked2.observe(lifecycleRegistry) {
            if (it == true) {
                binding.eS.isChecked = false
            }
            viewModel.getStates(binding.kst, binding.kdns)
        }
    }
}