package com.helixtech.ojt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebfaseMessagingService : FirebaseMessagingService() {

    //When the token generate
    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.d(TAG, "Refreshed token: $s")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        //Toast.makeText(, remoteMessage.toString(),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Refreshed token: " + remoteMessage.from!!)

        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")

            val mhandler: Handler = Handler(Looper.getMainLooper())
            mhandler.postDelayed(object : Runnable {

                override fun run() {
                    Toast.makeText(this@MyFirebfaseMessagingService, "FCM : ${it.title}, ${it.body}", Toast.LENGTH_SHORT).show()
                }

            },0)

            showNotification(it.title, it.body)
            //Toast.makeText(applicationContext, "FCM : ${it.title}, ${it.body}", Toast.LENGTH_SHORT).show()
        }

        //showNotification(remoteMessage.data["title"], remoteMessage.data["message"])
        Log.d("remoteMessage", "${remoteMessage.data["title"]}, ${remoteMessage.data["message"]}")


        val mhandler2: Handler = Handler(Looper.getMainLooper())
        mhandler2.postDelayed(object  : Runnable{
            override fun run() {
                Toast.makeText(applicationContext,"${remoteMessage.data["title"]}, ${remoteMessage.data["message"]}",Toast.LENGTH_SHORT).show()
            }
        },0)




    }



    private fun showNotification(title: String?, message: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = "FCM"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.helixtech_logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build())
    }

    companion object {
        private val TAG = "FCM"
    }


/*    val lamda01 = {par1: Int, par2: Int ->
        println("Here is the lamda01")
        par1+par2
    }

    val lamda02 : (Int) -> Int = {par1 : Int -> par1*10}
    val lamda03 : (Int, Int) -> Int = {par1 : Int, par2 : Int -> par1*par2}
    val lamda04 : (String, Int) -> String = {
        hello : String, num : Int -> "$hello number is $num"
    }*/
}
