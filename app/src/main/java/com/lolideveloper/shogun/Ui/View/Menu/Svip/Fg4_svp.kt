package com.lolideveloper.shogun.Ui.View.Menu.Svip

import android.content.Context
import android.content.Intent
import android.os.Process
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.lolideveloper.shogun.Core.LifecycleOwnerVirtual
import com.lolideveloper.shogun.Service.fltsp
import com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip.FG4ViewModel
import com.lolideveloper.shogun.databinding.OpBinding
import javax.inject.Inject

class Fg4_svp @Inject constructor(
    private val context: Context,
    private val viewModel: FG4ViewModel
) : LinearLayout(context) {
    private var _binding: OpBinding? = null
    private val binding get() = _binding!!
    private val lifecycleRegistry = LifecycleOwnerVirtual()

    init {
        _binding = OpBinding.inflate(LayoutInflater.from(context), this, true)
        lifecycleRegistry.onStart()
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        /* Game Layout */
        binding.game.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsChecked(isChecked)
            if (isChecked) {

            } else {

            }
        }
        binding.rdgp.setOnCheckedChangeListener { radioGroup, checkid ->
            when (checkid) {
                binding.ze.id -> {

                }

                binding.on.id -> {

                }

                binding.tw.id -> {

                }

                binding.th.id -> {

                }

                binding.fo.id -> {

                }
            }

            binding
        }

        binding.vd.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.coVd.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.re.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.coRe.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.couFps.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        binding.maxFps.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.coFps.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        /* Menu Settings */
        binding.menu.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsChecked2(isChecked)
            if (isChecked) {

            } else {

            }
        }

        binding.kill.setOnClickListener {
            context.stopService(Intent(context, fltsp::class.java))
            Process.killProcess(Process.myPid())
        }

    }

    private fun initObservers() {
        viewModel.isChecked.observe(lifecycleRegistry) {
            if (it == true) {
                binding.menu.isChecked = false
            }
            viewModel.getStates(binding.gmLy, binding.mnLy)
        }
        viewModel.isChecked2.observe(lifecycleRegistry) {
            if (it == true) {
                binding.game.isChecked = false
            }
            viewModel.getStates(binding.gmLy, binding.mnLy)
        }
    }
}