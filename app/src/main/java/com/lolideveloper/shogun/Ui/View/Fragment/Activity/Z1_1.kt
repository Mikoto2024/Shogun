package com.lolideveloper.shogun.Ui.View.Fragment.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.lolideveloper.shogun.NetWork.Update
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Dialog.D2
import com.lolideveloper.shogun.Ui.ViewModel.Fragment.Z1ViewModel
import com.lolideveloper.shogun.databinding.Z11Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class z1_1 : Fragment() {
    private var _binding: Z11Binding? = null
    private val binding get() = _binding!!

    private val viewModel: Z1ViewModel by viewModels()

    private lateinit var update: Update


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = Z11Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        update = Update(requireActivity())
        update.initRemoteConfig(binding.c1)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
        initData()
    }

    private fun initListeners() {
        val layout = binding.c1
        val getD2 = D2(requireActivity(), layout)
        binding.edtUserID.doAfterTextChanged {
            viewModel.getLogin(it.toString(), binding.edtPassword.text.toString())
        }
        binding.edtPassword.doAfterTextChanged {
            viewModel.getLogin(binding.edtUserID.text.toString(), it.toString())
        }

        binding.inf.setOnClickListener { getD2.show() }

        binding.lg.setOnClickListener {
            viewModel.onResponse.initLogin(
                requireContext(),
                binding.edtUserID.text.toString(),
                binding.edtPassword.text.toString()
            ) { bool ->
                if (bool) {
                    viewModel.onStorage.svUser(
                        binding.edtUserID.text.toString(), binding.edtPassword.text.toString(), null
                    )
                    val result = Bundle()
                    result.putString("UserID", binding.edtUserID.text.toString())
                    result.putString("Password", binding.edtPassword.text.toString())
                    result.putBoolean("State", true)
                    parentFragmentManager.setFragmentResult("state", result)
                    val pager = requireActivity().findViewById<ViewPager2>(R.id.z0)
                    pager.currentItem = 2
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.isEnabled.observe(viewLifecycleOwner) {
            binding.lg.isEnabled = it
        }
    }

    private fun initData() {
        binding.edtUserID.setText(viewModel.onStorage.getUser(0))
        binding.edtPassword.setText(viewModel.onStorage.getUser(1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}