package com.lolideveloper.shogun.Ui.ViewModel.Fragment

import android.app.ActivityManager
import android.content.Context
import android.opengl.EGL14
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.text.Spannable
import android.text.SpannableString
import android.text.format.Formatter
import android.text.style.TextAppearanceSpan
import androidx.lifecycle.ViewModel
import com.lolideveloper.shogun.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class InfAppViewModel @Inject constructor() : ViewModel() {


    fun getDevicefeatures(context: Context): SpannableString? {
        val ShadowGreen = TextAppearanceSpan(context, R.style.GreenShadowTextStyle)

        val supportedArchitectures = Build.SUPPORTED_ABIS
        val architecturesText = supportedArchitectures.joinToString(", ")

        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalMemory = memoryInfo.totalMem
        val totalMemoryStr = Formatter.formatFileSize(context, totalMemory)

        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val deviceName = if (model.startsWith(manufacturer)) {
            model
        } else {
            "$manufacturer $model"
        }
        val cpuInfo = try {
            val process = ProcessBuilder("/system/bin/cat", "/proc/cpuinfo")
                .redirectErrorStream(true)
                .start()
            val inputStream = process.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            var processorName: String? = null

            while (reader.readLine().also { line = it } != null) {
                if (line!!.contains("Processor")) {
                    val parts = line!!.split(":")
                    if (parts.size > 1) {
                        processorName = parts[1].trim()
                        break
                    }
                }
            }

            reader.close()
            process.destroy()

            processorName
        } catch (e: Exception) {
            null
        }

        val txt1 =
            "Device features :${space()}" +
                    "OS : Android ${Build.VERSION.RELEASE}${space()}" +
                    "Processor : ${cpuInfo}${space()}" +
                    "Processor Architecture : $architecturesText${space()}" +
                    "Memory : $totalMemoryStr${space()}" +
                    "Storage : ${StorageInf()}GB${space()}" +
                    "Device : $deviceName ${Build.DEVICE}"

        val spannableString = SpannableString(txt1)
        spannableString.setSpan(ShadowGreen, 0, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannableString
    }

    fun getStrings(context: Context, i: Int): SpannableString? {
        val ShadowBlue = TextAppearanceSpan(context, R.style.BlueShadowTextStyle)
        if (i == 0) {
            val txt1 =
                "Minimum :\n" +
                        "OS : Android 7.0 ${space()}" +
                        "Processor: Qualcomm Snapdragon 680${space()}" +
                        "Memory : 6 GB${space()}" +
                        "Storage : 32 GB${space()}"
            val spannableString = SpannableString(txt1)
            spannableString.setSpan(ShadowBlue, 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannableString
        }
        if (i == 1) {
            val txt2 =
                "Recommended :\n" +
                        "OS : Android 11${space()}" +
                        "Processor: Qualcomm Snapdragon 865+${space()}" +
                        "Memory: 8 - 12 - 16 - 24 GB${space()}" +
                        "Storage : ∞ GB${space()}"
            val spannableString = SpannableString(txt2)
            spannableString.setSpan(ShadowBlue, 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannableString
        }
        return null
    }

    private fun StorageInf(): Long {
        val rutaAlmacenamientoInterno = Environment.getDataDirectory().path
        val statFs = StatFs(rutaAlmacenamientoInterno)
        val bloquesTotales = statFs.blockCountLong
        val tamanoBloque = statFs.blockSizeLong
        val capacidadTotalBytes = bloquesTotales * tamanoBloque

        // Convertir a gigabytes
        val capacidadTotalGB = capacidadTotalBytes / (1024 * 1024 * 1024)

        return capacidadTotalGB
    }

    private fun OpenGlInf(): String {
        return "${EGL14.EGL_VERSION}"
    }

    /* Spaces */
    private fun space(): String {
        return "\n"
    }
}