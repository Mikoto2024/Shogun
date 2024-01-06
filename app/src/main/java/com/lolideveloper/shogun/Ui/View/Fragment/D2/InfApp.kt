package com.lolideveloper.shogun.Ui.View.Fragment.D2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lolideveloper.shogun.Ui.ViewModel.Fragment.InfAppViewModel
import com.lolideveloper.shogun.Utils.Util.Companion.mToast
import com.lolideveloper.shogun.databinding.InfappBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfApp : Fragment() {
    private var _binding: InfappBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InfAppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = InfappBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initText()
        initListeners()
    }

    private fun initText() {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                binding.txt1.setText(viewModel.getDevicefeatures(requireContext()))
            }
            binding.textView7.setText("System Requirements :")
            binding.txt2.setText(viewModel.getStrings(requireContext(), 0))
            binding.txt3.setText(viewModel.getStrings(requireContext(), 1))
        }
    }

    private fun initListeners() {
        binding.wechat.setOnClickListener { mToast(requireContext(), 0, "null") }
        binding.discord.setOnClickListener { startUrl("https://discord.gg/XugXDNcKB4") }
        binding.telegram.setOnClickListener { startUrl("https://t.me/+81HSuRgqB0A3ZDlk") }
    }

    private fun startUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}