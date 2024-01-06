package com.lolideveloper.shogun.Ui.View.Fragment.D2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lolideveloper.shogun.Ui.ViewModel.Fragment.UserInfViewModel
import com.lolideveloper.shogun.databinding.UsrinfBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class UsrInf : Fragment() {
    private var _binding: UsrinfBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserInfViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UsrinfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
            viewModel.onResponse.initLoadData(requireActivity(), binding.i1, binding.i2, binding.i3)
    }

    private fun initObservers() {
    }
}