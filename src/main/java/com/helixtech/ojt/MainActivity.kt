package com.helixtech.ojt

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gmap_btn.setOnClickListener{
            val gmapIntent = Intent(this@MainActivity, GoogleMapsActivity::class.java)
            startActivity(gmapIntent)
        }

        nfc_btn.setOnClickListener {
            val nfcIntent = Intent(this@MainActivity, NFCActivity::class.java)
            startActivity(nfcIntent);
        }

        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and Toast
                    Log.d(TAG, token)
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })

    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
