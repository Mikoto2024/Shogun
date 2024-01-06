package com.lolideveloper.shogun.Utils

import android.app.ActionBar.LayoutParams
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.lolideveloper.shogun.Adapters.ViewPagerAdapter
import com.lolideveloper.shogun.R

class Util {
    companion object {
        var mMediaPlayer = MediaPlayer()
        var mCurrentVideoPosition = 0
        const val getPackageGame = "com.studiowildcard.wardrumstudios.ark"
        const val getSplashGame = "com.epicgames.ue4.SplashActivity"
        const val getGameActivity = "com.epicgames.ue4.GameActivity"
        fun mToast(context: Context, code: Int, txt: String) {
            val mLayout = LinearLayout(context)
            mLayout.gravity = Gravity.CENTER
            val mText = TextView(context)
            mText.gravity = Gravity.CENTER
            mText.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            mText.setShadowLayer(10f, 2f, 5f, Color.parseColor("#137AEF"))

            mLayout.addView(mText)
            when (code) {
                0 -> {
                    mLayout.setBackgroundResource(R.drawable.bk_toast)
                    mText.text = "$txt"
                    Toast(context).apply {
                        setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 128)
                        duration = Toast.LENGTH_SHORT
                        view = mLayout
                    }.show()
                }
            }
        }


        /* I recommend getVideoProportion = value 1.40 float */
        fun fxVideoView(context: Context, getVideoProportion: Float, view: VideoView) {
            val videoProportion = getVideoProportion
            val screenWidth = context.resources.displayMetrics.widthPixels
            val screenHeight = context.resources.displayMetrics.heightPixels
            val screenProportion = screenHeight.toFloat() / screenWidth.toFloat()
            val lp: ViewGroup.LayoutParams = view.layoutParams
            if (videoProportion < screenProportion) {
                lp.height = screenHeight
                lp.width = (screenHeight.toFloat() / videoProportion).toInt()
            } else {
                lp.width = (screenWidth.toFloat() * videoProportion).toInt()
                lp.height = (screenHeight.toFloat() * videoProportion).toInt()
            }
            view.layoutParams = lp
        }

        fun mViewPager2(
            context: AppCompatActivity, mView: ViewPager2, list: ArrayList<Fragment>, num: Int
        ) {
            val adapter = ViewPagerAdapter(list, context.supportFragmentManager, context.lifecycle)
            mView.adapter = adapter
            mView.currentItem = num
        }

        fun mWindowParams(w: Int, h: Int, xpos: Int, ypos: Int): WindowManager.LayoutParams {
            val params = WindowManager.LayoutParams(
                w,
                h,
                xpos,
                ypos,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    WindowManager.LayoutParams.TYPE_PHONE
                },
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                PixelFormat.TRANSLUCENT
            )
            return params
        }

        fun mVideoView(Video: VideoView, packageName: String, address: Int) {
            Video.requestFocus()
            val Uri = Uri.parse(
                "android.resource://"

                        + packageName

                        + "/"

                        + address
            )

            Video.setVideoURI(Uri)

            Video.start()

            Video.setOnPreparedListener { mp ->

                mMediaPlayer = mp

                mMediaPlayer.isLooping = true

                if (mCurrentVideoPosition != 0) {

                    mMediaPlayer.seekTo(mCurrentVideoPosition)

                    mMediaPlayer.start()

                }
            }
        }

        fun copy(context: Context, text: String) {
            val clipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("txt", text)
            clipboardManager.setPrimaryClip(clipData)
            mToast(context, 0, "Copied : $text")
        }
    }
}