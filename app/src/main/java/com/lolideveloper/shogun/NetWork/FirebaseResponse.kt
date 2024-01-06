package com.lolideveloper.shogun.NetWork

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings.Secure
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.lolideveloper.shogun.Core.Loader.Companion.getID
import com.lolideveloper.shogun.Service.fltsp
import com.lolideveloper.shogun.Ui.Model.Inf
import com.lolideveloper.shogun.Ui.Model.UserID
import com.lolideveloper.shogun.Utils.Storage
import com.lolideveloper.shogun.Utils.Util.Companion.copy
import com.lolideveloper.shogun.Utils.Util.Companion.mToast
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("HardwareIds")
@Singleton
class FirebaseResponse @Inject constructor(
    private val firebase: FirebaseClient,
    private val mStorage: Storage
) {
    private lateinit var mUserID: UserID
    private lateinit var mInf: Inf
    private val deviceID = getID()

    /* Login , Register , Verify Code ( Z1_1 , D1 ,D2 ) */
    fun initLogin(context: Context, userID: String, password: String, check: (Boolean) -> Unit) {
        mUserID = UserID(userID, password, "", "")

        firebase.db.collection("Users").document(mUserID.userid).get().addOnSuccessListener {
            if (!it.exists()) {
                mToast(context, 0, "No exists")
            } else {
                onCheckDeviceID() { bool ->
                    if (bool) {
                        val getUserID = it.getString("UserID").toString()
                        val getPassword = it.getString("Password").toString()
                        if (mUserID.userid == getUserID && mUserID.password == getPassword) {
                            check(true)
                        } else {
                            check(false)
                        }
                    } else {
                        mToast(context, 0, "Different device : $deviceID")
                    }
                }
            }
        }
    }

    fun initRegister(context: Context, userID: String, password: String) {
        mUserID = UserID(userID, password, "", "")
        onCheckAndroidID(context) { bool ->
            if (bool) {
                onRegister(context)
            } else {
                mToast(context, 0, "Device already registered")
            }
        }
    }

    fun initVerifyCode(context: Context, userID: String, password: String, code: String) {
        mUserID = UserID(userID, password, code, "")
        onCheckDeviceID { bool ->
            if (bool) {
                firebase.db.collection("Users").document(mUserID.userid).get()
                    .addOnSuccessListener {
                        val getDeviceID = it.getString("ID")
                        val getUserID = it.getString("UserID")
                        val getPassword = it.getString("Password")
                        val getCode = it.getString("Code")
                        if (getUserID == mUserID.userid && getPassword == mUserID.password && getCode == mUserID.code) {
                            if (deviceID == getDeviceID) {
                                mStorage.svUser(null, null, mUserID.code)
                                context.startService(Intent(context, fltsp::class.java))
                                mToast(context, 0, "Success!!")
                            }
                        }
                    }
            } else {
                mToast(
                    context,
                    0,
                    "Security: A strange behavior was detected, it is recommended to reinstall the application to avoid formatting your device"
                )
            }
        }
    }

    /* Load Data UserID , Passowrd , Code ( Z1_1 , D2 - UserInf ) */
    @SuppressLint("SetTextI18n")
    fun initLoadData(context: Context, userid: TextView, password: TextView, code: TextView) {
        mInf = Inf("", "", "")
        val AndroidID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        firebase.db.collection("ID").document(AndroidID).addSnapshotListener { value, error ->
            if (value == null) {
                userid.text = "null"
                password.text = "null"
                code.text = "null"
            } else {
                mInf = Inf(
                    value.getString("UserID").toString(),
                    value.getString("Password").toString(),
                    value.getString("Code").toString()
                )
                userid.text = "UserID : " + mInf.userid
                password.text = "Password :  " + mInf.password
                code.text = "Code : " + mInf.code
            }
        }
        userid.setOnClickListener { copy(context, mInf.userid) }
        password.setOnClickListener { copy(context, mInf.password) }
        code.setOnClickListener { copy(context, mInf.code) }
    }

    /* Check Update ( Update , UpdateView ) */
    fun initUpdate(context: Context) {
        firebase.db.collection("Options").document("Links").get().addOnSuccessListener {
            if (it.exists()) {
                val getLink = it.data?.get("Update").toString()
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("$getLink")
                ContextCompat.startActivity(context, openURL, null)
            }
        }
    }

    fun redeemCode(context: Context, code: String) {
        val AndroidID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        onCheckAccount(context) {
            if (!it) {
                mToast(context, 0, "Account not created")
            } else {
                firebase.db.collection("Codes").document(code).get().addOnSuccessListener {
                    if (!it.exists()) {
                        mToast(context, 0, "This code does not exist")
                    } else {
                        val getState = it.getBoolean("State")
                        if (getState == false) {
                            mToast(context, 0, "Code already redeemed")
                        } else {
                            firebase.db.collection("Users").document(mUserID.userid).set(
                                hashMapOf(
                                    "ID" to getID(),
                                    "UserID" to mUserID.userid,
                                    "Password" to mUserID.password,
                                    "Code" to code
                                )
                            ).addOnSuccessListener {
                                firebase.db.collection("ID").document(AndroidID).set(
                                    hashMapOf(
                                        "AndroidID" to AndroidID,
                                        "DevideID" to deviceID,
                                        "UserID" to mUserID.userid,
                                        "Password" to mUserID.password,
                                        "Code" to code
                                    )
                                ).addOnSuccessListener {
                                    mToast(context, 0, "Added code : $code")
                                }
                                firebase.db.collection("Codes").document(code).set(
                                    hashMapOf(
                                        "State" to false
                                    )
                                )
                            }
                        }

                    }
                }
            }
        }
    }

    private fun onCheckAccount(context: Context, check: (Boolean) -> Unit) {
        if (::mUserID.isInitialized) {
            firebase.db.collection("Users").document(mUserID.userid).get().addOnSuccessListener {
                if (!it.exists()) {
                    check(false)
                } else {
                    check(true)
                }
            }
        } else {
            mToast(context, 0, "Login before redeeming a code")
        }
    }

    /* Complements Login , Register , Verify Code */
    private fun onCheckDeviceID(check: (Boolean) -> Unit) {
        firebase.db.collection("Users").document(mUserID.userid).get().addOnSuccessListener {
            if (!it.exists()) {
                check(false)
            } else {
                val getID = it.getString("ID").toString()
                if (deviceID == getID) {
                    check(true)
                } else {
                    check(false)
                }
            }
        }
    }

    private fun onRegister(context: Context) {
        firebase.db.collection("Users").document(mUserID.userid).get().addOnSuccessListener {
            if (!it.exists()) {
                firebase.db.collection("Users").document(mUserID.userid).set(
                    hashMapOf(
                        "UserID" to mUserID.userid, "Password" to mUserID.password, "ID" to deviceID
                    )
                ).addOnSuccessListener {
                    mStorage.svUser(mUserID.userid, mUserID.password, null)
                    onRegisterID(context)
                }
            } else {
                mToast(context, 0, "UserID already existing")
            }
        }
    }

    private fun onRegisterID(context: Context) {
        val AndroidID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        firebase.db.collection("ID").document(AndroidID).set(
            hashMapOf(
                "AndroidID" to AndroidID,
                "DevideID" to deviceID,
                "UserID" to mUserID.userid,
                "Password" to mUserID.password
            )
        ).addOnSuccessListener {
            mToast(context, 0, "Successfully Registered")
        }
    }

    private fun onCheckAndroidID(context: Context, check: (Boolean) -> (Unit)) {
        val AndroidID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        firebase.db.collection("ID").document(AndroidID).get().addOnSuccessListener {
            if (!it.exists()) {
                check(true)
            } else {
                check(false)
            }
        }
    }

}
