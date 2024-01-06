package com.lolideveloper.shogun.NetWork

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Update @Inject constructor(private val context: FragmentActivity) {

    val currentVersionCode: Double = 0.1

    var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null

    fun initRemoteConfig(I: ConstraintLayout) {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        val firebaseDefaultMap = HashMap<String, Any>()
        firebaseDefaultMap[VERSION_CODE_KEY] = currentVersionCode
        mFirebaseRemoteConfig!!.setDefaultsAsync(firebaseDefaultMap)

        mFirebaseRemoteConfig!!.setConfigSettingsAsync(
            FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(TimeUnit.SECONDS.toSeconds(0))
                .build()
        )
        //Fetching remote firebase version_code value here
        mFirebaseRemoteConfig!!.fetch().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //activate most recently fetch config value
                mFirebaseRemoteConfig!!.activate().addOnCompleteListener { task2 ->
                    if (task2.isSuccessful) {
                        //calling function to check if new version is available or not
                        val latestAppVersion =
                            mFirebaseRemoteConfig!!.getDouble(VERSION_CODE_KEY).toDouble()
                        checkForUpdate(latestAppVersion, I)
                    }
                }
            }
        }
    }

    fun checkForUpdate(latestAppVersion: Double, I: ConstraintLayout) {
        if (latestAppVersion != currentVersionCode) {
            val UpdateView = UpdateView(context, I)
            UpdateView.show()
        }
    }

    companion object {
        private const val VERSION_CODE_KEY = "Stable"
    }
}
