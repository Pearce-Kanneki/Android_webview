package com.kanneki.webview.domain.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.request.target.NotificationTarget
import com.kanneki.webview.R
import com.kanneki.webview.domain.model.fcmMessageData
import com.kanneki.webview.domain.model.toFcmMessageData
import com.kanneki.webview.utils.LogUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    private val CHANNEL_ID = "Coder"
    private var messageData = fcmMessageData()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        LogUtils.showDebugLog("Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        LogUtils.showDebugLog("onMessageReceived: ${message.data}")

        getSystemServiceByO()
        messageData = message.data.toFcmMessageData()

        getPendingIntent(getIntent()).also { pendingIntent ->
            val builder = getNotificationCompat(pendingIntent)
            NotificationManagerCompat.from(this).apply {
                notify(1, builder.build())
            }
        }
    }

    /**
     * 取得 NotificationManager,在SDK大於26時(Android 8.0)
     */
    private fun getSystemServiceByO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "DemoCode",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }
    }
    
    private fun getIntent(): Intent {
        return Intent(this, NotificationTarget::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("data", messageData.key1)
        }
    }

    private fun getPendingIntent(intent: Intent): PendingIntent? {
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    private fun getNotificationCompat(pendingIntent: PendingIntent?) : NotificationCompat.Builder {
        return NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.jetpack_nav_icon)
            setContentTitle(messageData.title)
            setContentText(messageData.body)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_HIGH
            setCategory(NotificationCompat.CATEGORY_MESSAGE)
            setContentIntent(pendingIntent)
        }
    }
}