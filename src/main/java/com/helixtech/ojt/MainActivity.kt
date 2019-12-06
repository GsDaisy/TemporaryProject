package com.helixtech.ojt

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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


    }
}
