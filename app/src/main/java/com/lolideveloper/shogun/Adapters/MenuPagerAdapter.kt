package com.lolideveloper.shogun.Adapters

import android.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.lolideveloper.shogun.R

class MenuPagerAdapter(private val views: List<View>) : RecyclerView.Adapter<MenuPagerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val myLayout: LinearLayout = view.findViewById(R.id.my_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pager, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = views[position]
        val MATCH_PARENT =
            ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
            )
        view.layoutParams = MATCH_PARENT
        holder.myLayout.addView(view)
    }
    override fun getItemCount(): Int {
        return views.size
    }
}
