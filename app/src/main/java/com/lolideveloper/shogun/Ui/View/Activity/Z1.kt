package com.lolideveloper.shogun.Ui.View.Activity

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Dialog.D0
import com.lolideveloper.shogun.Utils.Storage
import com.lolideveloper.shogun.Utils.Util.Companion.fxVideoView
import com.lolideveloper.shogun.Utils.Util.Companion.mCurrentVideoPosition
import com.lolideveloper.shogun.Utils.Util.Companion.mMediaPlayer
import com.lolideveloper.shogun.Utils.Util.Companion.mVideoView
import com.lolideveloper.shogun.databinding.Z1Binding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class z1 : AppCompatActivity() {
    private lateinit var binding: Z1Binding

    override fun attachBaseContext(newBase: Context) {
        val storage = Storage(newBase)
        val context: Context = mChangeLanguage(newBase, storage.getLanguage())
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Z1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initDialog()
        fMyVideoView()
    }

    private fun mChangeLanguage(context: Context, key: String): Context {
        var context = context
        val resources = context.resources
        val configuration = resources.configuration
        val systemLocale: Locale
        systemLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales[0]
        } else {
            configuration.locale
        }
        if (key != "" && systemLocale.language != key) {
            val locale = Locale(key)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale)
            } else {
                configuration.locale = locale
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                context.resources.updateConfiguration(
                    configuration, context.resources.displayMetrics
                )
            }
        }
        return ContextWrapper(context)
    }

    private fun initDialog() {
        D0(this, binding.z0).show()
    }

    private fun fMyVideoView() {
        fxVideoView(this, 1.34f, binding.f0) //  1.36
        mVideoView(binding.f0, packageName, R.raw.f0)
    }

    override fun onResume() {
        super.onResume()
        binding.f0.start()
    }

    override fun onPause() {
        super.onPause()
        mCurrentVideoPosition = mMediaPlayer.currentPosition
        binding.f0.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
        mMediaPlayer.release()
    }
}
