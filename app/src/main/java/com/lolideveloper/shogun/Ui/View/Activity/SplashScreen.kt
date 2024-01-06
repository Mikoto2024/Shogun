package com.lolideveloper.shogun.Ui.View.Activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.ViewModel.SplashViewModel
import com.lolideveloper.shogun.databinding.SplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding

    private val viewModel: SplashViewModel by viewModels()

    @SuppressLint("ObsoleteSdkInt")
    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splash.setKeepOnScreenCondition { false }
        init()
    }

    private fun init() {
        initListeners()
        initTime()
    }

    private fun initListeners() {
        binding.gif.setImageResource(R.drawable.lg)
    }

    private fun initTime() {
        object : CountDownTimer(2400, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                viewModel.initActivity(this@SplashScreen)
                finish()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}
