package com.example.mypickeoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class UserSession : AppCompatActivity() {

    var txtusern: EditText? = null


    var btnstart: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_session)

        txtusern=findViewById(R.id.etUserName)
        btnstart=findViewById(R.id.btnStart)

        btnstart?.setOnClickListener(View.OnClickListener {

        })


    }
}