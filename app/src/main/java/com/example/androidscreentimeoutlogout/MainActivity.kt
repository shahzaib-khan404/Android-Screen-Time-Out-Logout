package com.example.androidscreentimeoutlogout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ScreenTimeOutUtil.LogoutCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onPause() {
        super.onPause()
        ScreenTimeOutUtil.stopLogoutTimer()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        ScreenTimeOutUtil.startTimer(this, this)
        Log.e("TAG", "User interacting with screen")
    }

    override fun onStart() {
        super.onStart()
        ScreenTimeOutUtil.startTimer(this, this)
    }


    override fun makeLogoutCall() {
        Log.e("TAG", "Logging Out")
        CoroutineScope(Dispatchers.Main).launch {
            startActivity(
                Intent(
                    this@MainActivity,
                    SessionTimeoutActivity::class.java
                )
            )
            finish()

        }
    }
}