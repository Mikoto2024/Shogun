package com.lolideveloper.shogun.Ui.View.Fragment.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import com.lolideveloper.shogun.Ui.Model.UserID
import com.lolideveloper.shogun.Ui.ViewModel.Fragment.Z2ViewModel
import com.lolideveloper.shogun.Utils.Storage
import com.lolideveloper.shogun.databinding.Z12Binding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class z1_2 @Inject constructor() : Fragment() {
    private var _binding: Z12Binding? = null
    private val binding get() = _binding!!

    private var STATE: Boolean = false

    private val viewModel: Z2ViewModel by viewModels()

    @Inject
    protected lateinit var mStorage: Storage

    private lateinit var mUserID: UserID
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = Z12Binding.inflate(inflater, container, false)
        parentFragmentManager.setFragmentResultListener(
            "state",
            this,
            FragmentResultListener { requestKey, result ->
                val getUserID = result.getString("UserID").toString()
                val getPassword = result.getString("Password").toString()
                val isEnabled = result.getBoolean("State", false)
                mUserID = UserID(getUserID, getPassword, "", "")
                STATE = isEnabled
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vrfy.isEnabled = false
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
        initData()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.edtCode.imeOptions = EditorInfo.IME_FLAG_NO_FULLSCREEN
        binding.edtCode.doAfterTextChanged { viewModel.getCode(it.toString()) }
        binding.vrfy.setOnClickListener {
            viewModel.onResponse.initVerifyCode(
                requireContext(),
                mUserID.userid,
                mUserID.password,
                binding.edtCode.text.toString())
        }
    }

    private fun initObservers() {
        binding.vrfy.isEnabled = viewModel.onCheckButton()
        viewModel.code.observe(viewLifecycleOwner) {
            if (STATE == true) {
                binding.vrfy.isEnabled = viewModel.onCheckButton()
            }
        }
    }

    private fun initData() {
        binding.edtCode.setText(mStorage.getUser(2))
    }

    override fun onResume() {
        super.onResume()
        if (STATE == true) {
            binding.vrfy.isEnabled = viewModel.onCheckButton()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}