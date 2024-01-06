package com.lolideveloper.shogun.Ui.View.Menu.Svip

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.lolideveloper.shogun.Core.LifecycleOwnerVirtual
import com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip.FG1ViewModel
import com.lolideveloper.shogun.databinding.H9Binding
import javax.inject.Inject

class Fg1_svp @Inject constructor(
    private val context: Context, private val viewModel: FG1ViewModel
) : LinearLayout(context) {
    private var _binding: H9Binding? = null
    private val binding get() = _binding!!
    private val lifecycleRegistry = LifecycleOwnerVirtual()

    init {
        _binding = H9Binding.inflate(LayoutInflater.from(context), this, true)
        lifecycleRegistry.onStart()
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        /* init Esp View */
        //Esp Player

        binding.a0.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsEnabled(isChecked)
            viewModel.getIsChecked(binding.a0)
            if (isChecked) {

            } else {

            }
        }

        //Dead Player
        binding.dp.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        //Player Box
        binding.pb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        //Sleeping Player
        binding.sp.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        /* PvP Settings */
        // Activate Self Pointed
        binding.a1.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsEnabled2(isChecked)
            viewModel.getIsChecked2(binding.a1)
            viewModel.getIsGone(binding.a1)
            if (isChecked) {

            } else {

            }
        }
        // Bullet Track
        binding.track.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        // Radio Button Group
        binding.rdgroup.setOnCheckedChangeListener { radioGroup, checkId ->
            when (checkId) {
                binding.none.id -> {

                }

                binding.hd.id -> {

                }

                binding.ct.id -> {

                }

                binding.ls.id -> {

                }
            }
        }
        /* Aim Pov */
        binding.a2.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.getIsEnabled3(isChecked)
            if (isChecked) {

            } else {

            }
        }
        //Seekbar AimPov
        binding.apov.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc0.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        /* SpeedGlobal + SpeedPlayer + BigHead */

        binding.sksg.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc1.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.sksp.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc2.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.skbh.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc3.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        /* Extras */
        //Antenna
        binding.ant.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        //-Block Enemy
        binding.be.setOnCheckedChangeListener { buttonView, isChecked -> // Especial -> Switch
            if (isChecked) {

            } else {

            }
        }
        //No Recoil
        binding.nr.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        //Underwater Shot
        binding.unst.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        /* Night Vision + Pov + Wide View */
        binding.sknv.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc4.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.skpv.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc5.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.skwv.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.hc6.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun initObservers() {
        /* Esp */
        viewModel.getIsEnabled(binding.a0.isChecked)
        viewModel.checkIsEnabled.observe(lifecycleRegistry) {
            binding.dp.isEnabled = it
            binding.pb.isEnabled = it
            binding.sp.isEnabled = it
            binding.a1.isEnabled = it
        }
        viewModel.checkIsChecked.observe(lifecycleRegistry) {
            binding.dp.isChecked = it
            binding.pb.isChecked = it
            binding.sp.isChecked = it
            binding.a1.isChecked = it
        }
        /* Pvp Settings */
        viewModel.getIsEnabled2(binding.a1.isChecked)
        viewModel.checkIsEnabled2.observe(lifecycleRegistry) {
            binding.track.isEnabled = it
            binding.a2.isEnabled = it
        }

        viewModel.checkIsChecked2.observe(lifecycleRegistry) {
            binding.track.isChecked = it
            binding.a2.isChecked = it
            binding.none.isChecked = true
        }
        viewModel.getIsGone(binding.a1)
        viewModel.checkIsGone.observe(lifecycleRegistry) {
            binding.none.isEnabled = it
            binding.hd.isEnabled = it
            binding.ct.isEnabled = it
            binding.ls.isEnabled = it
        }

        viewModel.getIsEnabled3(binding.a2.isChecked)
        viewModel.checkIsEnabled3.observe(lifecycleRegistry) {
            binding.apov.isEnabled = it
        }
    }
}