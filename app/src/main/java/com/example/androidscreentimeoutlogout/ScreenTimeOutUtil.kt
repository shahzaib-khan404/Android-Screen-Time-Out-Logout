package com.example.androidscreentimeoutlogout

import android.app.ActivityManager
import android.content.Context
import kotlinx.coroutines.*
import java.util.*

object ScreenTimeOutUtil {

    interface LogoutCallback {
        fun makeLogoutCall()
    }

    private var logoutTimer: Timer? = null
    private const val LOGOUT_TIME: Long = 30000 // 5 minutes in milliseconds

    @Synchronized
    fun startTimer(context: Context, logoutCallback: LogoutCallback) {
        logoutTimer?.cancel()
        logoutTimer = null

        if (logoutTimer == null) {
            logoutTimer = Timer()

            logoutTimer?.schedule(object : TimerTask() {
                override fun run() {
                    cancel()
                    logoutTimer = null

                    CoroutineScope(Dispatchers.Main).launch {
                        val foreGround = checkForeground(context)

                        if (foreGround) {
                            logoutCallback.makeLogoutCall()
                        }
                    }
                }
            }, LOGOUT_TIME)
        }
    }

    @Synchronized
    fun stopLogoutTimer() {
        logoutTimer?.cancel()
        logoutTimer = null
    }

    private suspend fun checkForeground(context: Context): Boolean {
        return withContext(Dispatchers.Default) {
            isAppOnForeground(context)
        }
    }

    private fun isAppOnForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false
        val packageName = context.packageName
        for (appProcess in appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
                return true
            }
        }
        return false
    }
}
