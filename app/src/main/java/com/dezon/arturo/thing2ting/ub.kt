package com.dezon.arturo.thing2ting

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.app.Activity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_ub.*


class ub : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ub)
        val url = "www.urbandictionary.com"
        val b1 = findViewById(R.id.b1) as Button
        val w1 = findViewById(R.id.w1) as WebView
        val b2 = findViewById(R.id.b2) as Button




        @Throws(UnsupportedOperationException::class)

        fun buildUri(authority: String) : Uri {
            val builder = Uri.Builder()
            builder.scheme("https")
                    .authority(authority)
            return builder.build()
        }
        fun loadpage(){

            w1.loadUrl( url)

            try{
                val uri = buildUri(url )
            w1.loadUrl(uri.toString() )
        }catch(e: UnsupportedOperationException ){
            e.printStackTrace() }
        }
        b1!!.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 19) {
                w1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                loadpage()
            }
            }
        b2!!.setOnClickListener {
            val intent = Intent(this, ub2 ::class.java)
            startActivity(intent)
        }



    }




    }






