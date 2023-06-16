package com.example.mypickeoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var txtAnswer=findViewById<TextView>(R.id.txtAnswer)
        var txtNot=findViewById<TextView>(R.id.txtNot)
        var btnBack=findViewById<Button>(R.id.btnBack)

        txtAnswer.text="Materiales Pickeados : "+MainActivity.found+"/"+MainActivity.totalQuestions
        txtNot.text="Materiales No Encontrados : "+MainActivity.notfound+"/"+MainActivity.totalQuestions

        btnBack.setOnClickListener(){
            val intent = Intent(applicationContext, UserSession::class.java)
            startActivity(intent)
            finish()
        }
    }
}