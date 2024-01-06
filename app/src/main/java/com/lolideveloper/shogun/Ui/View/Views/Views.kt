package com.lolideveloper.shogun.Ui.View.Views

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.lolideveloper.shogun.View.Black_Two
import com.lolideveloper.shogun.View.Blue_Two

class Views {
    companion object {
        private val MATCH_PARENT =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        private val WRAP_CONTENT = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        private val MATCH_WRAP = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        private val WRAP_MATCH = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        fun mTextView(context: Context, text: String?): TextView {
            val TextView = TextView(context)
            TextView.layoutParams = MATCH_PARENT
            TextView.text = text
            TextView.setShadowLayer(10f, 2f, 5f, Blue_Two)
            return TextView
        }

        fun mButton(context: Context, text: String?, onClick: (View) -> Unit): Button {
            val Button = Button(context)
            Button.text = text
            Button.setBackgroundColor(Black_Two)
            Button.setShadowLayer(10f, 2f, 5f, Blue_Two)
            Button.setOnClickListener(onClick)
            return Button
        }
    }
}