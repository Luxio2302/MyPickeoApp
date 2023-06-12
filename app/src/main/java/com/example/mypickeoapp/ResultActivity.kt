package com.example.mypickeoapp

import android.annotation.SuppressLint
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

        txtAnswer.text="Your Score is : "+MainActivity.result+"/"+MainActivity.totalQuestions
        txtNot.text="Your Material Not Found Is : "+MainActivity.notfound+"/"+MainActivity.totalQuestions

        btnBack.setOnClickListener(){
            onBackPressed()
        }
    }
}