package com.lolideveloper.shogun.Ui.View.Dialog

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.PowerManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Fragment.Activity.z1_0
import com.lolideveloper.shogun.Ui.View.Fragment.Activity.z1_1
import com.lolideveloper.shogun.Ui.View.Fragment.Activity.z1_2
import com.lolideveloper.shogun.Utils.Util.Companion.mViewPager2
import com.lolideveloper.shogun.databinding.D0Binding
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.system.exitProcess

@Singleton
class D0 @Inject constructor(
    private val context: AppCompatActivity,
    private val mViewPager2: ViewPager2
) :
    Dialog(context, R.style.AlertDialogCustom) {
    private var _binding: D0Binding? = null
    private val binding get() = _binding!!
    private val list: ArrayList<Fragment> = arrayListOf(z1_0(), z1_1(), z1_2())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = D0Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        init()

    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.mtrue.setOnClickListener { initCheckPermission() }
        binding.mfalse.setOnClickListener { exitProcess(-1) }
        binding.TC.setText("1- We are not responsible for any data loss you may have if you attempt to reverse engineer \n2- I don't like homosexuals - Not homosexual")
    }

    private fun initCheckPermission() {
        val mlist = BooleanArray(5)
        val mPower = context.getSystemService(AppCompatActivity.POWER_SERVICE) as PowerManager

        if (!Settings.System.canWrite(context)) {
            initPermission(0)
        } else {
            mlist[1] = true
        }
        if (!Settings.canDrawOverlays(context)) {
            initPermission(1)
        } else {
            mlist[2] = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                initPermission(2)
            } else {
                mlist[3] = true
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                initPermission(2)
            } else {
                mlist[3] = true
            }
        }
        if (!mPower.isIgnoringBatteryOptimizations(context.packageName)) {
            initPermission(3)
        } else {
            mlist[4] = true
        }
        if (mlist[1] && mlist[2] && mlist[3] && mlist[4]) {
            mViewPager2(context, mViewPager2, list, 1)
            dismiss()
        }
    }

    private fun initPermission(i: Int) {
        when (i) {
            0 -> {
                context.startActivity(
                    Intent(
                        Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + context.applicationContext.packageName)
                    )
                )
            }

            1 -> {
                context.startActivity(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + context.applicationContext.packageName)
                    )
                )
            }

            2 -> {
                if (Build.VERSION.SDK_INT >= 30) {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            Uri.parse("package:" + context.applicationContext.packageName)
                        )
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0
                    )
                }
            }

            3 -> {
                context.startActivity(
                    Intent(
                        Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                        Uri.parse("package:" + context.applicationContext.packageName)
                    )
                )
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        _binding = null
    }
}