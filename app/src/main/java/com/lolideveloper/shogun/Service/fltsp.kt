package com.lolideveloper.shogun.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.lolideveloper.shogun.Adapters.MenuPagerAdapter
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Ui.View.Menu.Svip.Fg1_svp
import com.lolideveloper.shogun.Ui.View.Menu.Svip.Fg2_svp
import com.lolideveloper.shogun.Ui.View.Menu.Svip.Fg3_svp
import com.lolideveloper.shogun.Ui.View.Menu.Svip.Fg4_svp
import com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip.FG1ViewModel
import com.lolideveloper.shogun.Ui.ViewModel.Menu.Svip.FG2ViewModel
import com.lolideveloper.shogun.Utils.Util.Companion.mWindowParams
import com.lolideveloper.shogun.View.White
import com.lolideveloper.shogun.databinding.SpBinding
import pl.droidsonroids.gif.GifImageView


class fltsp : Service() {

    private lateinit var binding: SpBinding

    private lateinit var mViewIcon: ViewGroup

    private lateinit var WindowParamsOne: WindowManager.LayoutParams

    private lateinit var WindowParamsIcon: WindowManager.LayoutParams

    private lateinit var WindowManagerGlobal: WindowManager


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        binding = SpBinding.inflate(LayoutInflater.from(this))
        mViewIcon = LinearLayout(this)
        WindowManagerGlobal = applicationContext.getSystemService(WINDOW_SERVICE) as WindowManager
        WindowParamsIcon = mWindowParams(0, 0, 0, 0)
        WindowParamsOne = mWindowParams(0, 0, 0, 0)
        init()
    }

    private fun init() {
        fltsp()
    }

    private fun fltsp() {

        fun getCurrentDisplayMetrics(): DisplayMetrics {
            val dm = DisplayMetrics()
            WindowManagerGlobal.defaultDisplay.getMetrics(dm)
            return dm
        }

        /* Svip Menu*/
        fun calculateSizeAndPosition(
            params: WindowManager.LayoutParams, widthInDp: Int, heightInDp: Int
        ) {
            val dm = getCurrentDisplayMetrics()

            params.gravity = Gravity.END or Gravity.TOP
            params.width = (widthInDp * dm.density).toInt()
            params.height = (heightInDp * dm.density).toInt()
            params.x = 64
            params.y = 24
        }

        /* Icon Flaot */
        fun calculateSizeAndPositionIcon(
            params: WindowManager.LayoutParams, widthInDp: Int, heightInDp: Int
        ) {
            val dm = getCurrentDisplayMetrics()

            params.gravity = Gravity.CENTER
            params.width = (widthInDp * dm.density).toInt()
            params.height = (heightInDp * dm.density).toInt()
            params.x = 0
            params.y = 0
        }

        calculateSizeAndPosition(WindowParamsOne, 358, 362)

        calculateSizeAndPositionIcon(WindowParamsIcon, 34, 34)

        WindowManagerGlobal.addView(binding.root, WindowParamsOne)

        mViewIcon.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        val myGif = GifImageView(this)
        myGif.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        myGif.setImageResource(R.drawable.rem)

        mViewIcon.addView(myGif)


        //Icon GIF
        binding.spCl.setOnClickListener {
            WindowManagerGlobal.removeView(binding.root)
            WindowManagerGlobal.addView(mViewIcon, WindowParamsIcon)
        }
        mViewIcon.setOnClickListener {
            WindowManagerGlobal.removeView(mViewIcon)
            WindowManagerGlobal.addView(binding.root, WindowParamsOne)
        }
        mViewIcon.setOnTouchListener(object : View.OnTouchListener {
            val updateFloatingLayoutParam = WindowParamsIcon

            var x = 0.0

            var y = 0.0

            var px = 0.0

            var py = 0.0

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        x = updateFloatingLayoutParam.x.toDouble()
                        y = updateFloatingLayoutParam.y.toDouble()

                        px = event.rawX.toDouble()
                        py = event.rawY.toDouble()
                    }

                    MotionEvent.ACTION_MOVE -> {
                        updateFloatingLayoutParam.x = (x + event.rawX - px).toInt()
                        updateFloatingLayoutParam.y = (y + event.rawY - py).toInt()

                        WindowManagerGlobal.updateViewLayout(
                            mViewIcon, updateFloatingLayoutParam
                        )
                    }
                }
                return false
            }
        })
        val list = listOf(
            Fg1_svp(this, FG1ViewModel()),
            Fg2_svp(this, FG2ViewModel()),
            Fg3_svp(this),
            Fg4_svp(this)
        )
        val Tab = binding.Tab
        val adapter = MenuPagerAdapter(list)


        binding.sp0.adapter = adapter
        binding.sp0.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Tab.selectTab(Tab.getTabAt(position))
            }
        })
        Tab.setTabTextColors(White, White)
        Tab.addTab(Tab.newTab().setText("Aimbot"))
        Tab.addTab(Tab.newTab().setText("Esp Objects"))
        Tab.addTab(Tab.newTab().setText("Svip"))
        Tab.addTab(Tab.newTab().setText("Options"))
        Tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.sp0.currentItem = tab.position
                    if (tab.position == 0) {

                    }
                    if (tab.position == 1) {

                    }
                    if (tab.position == 2) {

                    }
                    if (tab.position == 3) {

                    }

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}
