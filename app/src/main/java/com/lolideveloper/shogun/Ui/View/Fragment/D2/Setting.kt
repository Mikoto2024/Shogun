package com.lolideveloper.shogun.Ui.View.Fragment.D2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lolideveloper.shogun.R
import com.lolideveloper.shogun.Utils.Storage
import com.lolideveloper.shogun.databinding.SettingsBinding

class Setting : Fragment() {
    private var _binding: SettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = binding.root.context
        mSettings(context)
    }

    private fun mSettings(context: Context) {
        val mStorage = Storage(context)
        val mMenu = resources.getStringArray(R.array.Menu)
        val mlist = resources.getStringArray(R.array.language)

        val adapter = ArrayAdapter(
            context,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            mlist
        )
        val adapter2 = ArrayAdapter(
            context,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            mMenu
        )
        binding.language.setAdapter(adapter)
        binding.menu.setAdapter(adapter2)

        binding.menu.setText(mStorage.getSate(1), false)
        binding.language.setText(mStorage.getSate(2), false)

        binding.menu.onItemClickListener =
            AdapterView.OnItemClickListener { p0, view, p2, p3 ->
                if (p2.equals(0)) {
                    mStorage.svState(1, mMenu[0].toString())
                }
                if (p2.equals(1)) {
                    mStorage.svState(1, mMenu[1].toString())
                }
            }
        binding.language.onItemClickListener =
            AdapterView.OnItemClickListener { p0, view, p2, p3 ->
                if (p2.equals(0)) {
                    mStorage.svLanguage("en")
                    mStorage.svState(2, mlist[0].toString())
                }
                if (p2.equals(1)) {
                    mStorage.svLanguage("fr")
                    mStorage.svState(2, mlist[1].toString())
                }
                if (p2.equals(2)) {
                    mStorage.svLanguage("es")
                    mStorage.svState(2, mlist[2].toString())
                }
                if (p2.equals(3)) {
                    mStorage.svLanguage("zh")
                    mStorage.svState(2, mlist[3].toString())
                }
                if (p2.equals(4)) {
                    mStorage.svLanguage("ja")
                    mStorage.svState(2, mlist[4].toString())
                }
                if (p2.equals(5)) {
                    mStorage.svLanguage("ko")
                    mStorage.svState(2, mlist[5].toString())
                }
                if (p2.equals(6)) {
                    mStorage.svLanguage("ru")
                    mStorage.svState(2, mlist[6].toString())
                }
                if (p2.equals(7)) {
                    mStorage.svLanguage("ar")
                    mStorage.svState(2, mlist[7].toString())
                }
            }
    }
}