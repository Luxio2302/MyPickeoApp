package com.example.mypickeoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mypickeoapp.databinding.ActivityUserSessionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserSession : AppCompatActivity() {

    private lateinit var binding: ActivityUserSessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(View.OnClickListener {
            if (binding.etUserName.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, "Te falto poner el username", Toast.LENGTH_SHORT).show()
            } else {
                login(binding.etUserName.text.toString())
            }
        })
    }

    private fun login(userName: String) {
        val quesApi = RetrofitHelper.getInstance().create(EmployeeApi::class.java)

        GlobalScope.launch {
            val result = quesApi.login(userName)
            //Log.e("api response is ", result.body().toString())
            GlobalScope.launch(Dispatchers.Main) {
                if (result.body().toString().equals("ok")) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("username", userName)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "El usuario no existe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}