package com.lolideveloper.shogun.Ui.View.Dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.lolideveloper.shogun.Adapters.DialogPagerAdapter
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Fragment.D2.InfApp
import com.lolideveloper.shogun.Ui.View.Fragment.D2.Setting
import com.lolideveloper.shogun.Ui.View.Fragment.D2.UsrInf
import com.lolideveloper.shogun.Ui.ViewModel.Dialog.D2ViewModel
import com.lolideveloper.shogun.databinding.D2Binding
import java.lang.Math.abs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class D2 @Inject constructor(
    private val context: FragmentActivity,
    private val layout: ConstraintLayout
) : Dialog(context, R.style.AlertDialogCustom) {
    private var _binding: D2Binding? = null
    private val binding get() = _binding!!

    private val viewModel: D2ViewModel by context.viewModels()

    override fun onStart() {
        super.onStart()
        layout.isGone = true
        window?.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = D2Binding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
        initViewPager()
    }

    private fun initListeners() {
        binding.userinf.setOnClickListener { binding.pagerd2.currentItem = 0 }
        binding.information.setOnClickListener { binding.pagerd2.currentItem = 1 }
        binding.settings.setOnClickListener { binding.pagerd2.currentItem = 2 }
    }

    private fun initObservers() {
        viewModel.state.observe(context) {
            binding.pagerd2.currentItem = viewModel.getState()
        }
    }

    private fun initViewPager() {
        val list: ArrayList<Fragment> = arrayListOf(UsrInf(), InfApp(), Setting())
        val adapter = DialogPagerAdapter(list, context.supportFragmentManager, context.lifecycle)
        binding.pagerd2.adapter = adapter
        binding.pagerd2.currentItem = viewModel.getState()
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer { page, position ->
            page.alpha = 1 - abs(position)
        }

        binding.pagerd2.setPageTransformer(compositePageTransformer)
        binding.pagerd2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.svState(position)
            }
        })
    }

    override fun dismiss() {
        super.dismiss()
        layout.isGone = false
        setOnDismissListener(null)
    }

}