package com.helixtech.ojt

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nfc.*
import java.lang.StringBuilder
import kotlin.experimental.and

class NFCActivity : AppCompatActivity() {

    internal var nfcAdapter: NfcAdapter? = null
    internal lateinit var pendingIntent: PendingIntent
    //private static String tagNum=null;
    //internal var dataTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        //dataTxt = findViewById(R.id.data_txt)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

    }

    override fun onPause() {
        if (nfcAdapter != null) {
            nfcAdapter!!.disableForegroundDispatch(this)
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (nfcAdapter != null) {
            nfcAdapter!!.enableForegroundDispatch(this, pendingIntent, null, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        //val tag2 = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_DATA)

        if (tag != null) {


            val byteData :ByteArray= tag.id

            var sb: StringBuilder = StringBuilder();
            for(i in byteData){
                sb.append(String.format("%02X", i and 0xff.toByte()))
            }


            Toast.makeText(this, sb.toString() , Toast.LENGTH_SHORT).show()
            data_txt.setText(sb.toString())

        }

    }

    /* companion object {

     }*/

}
