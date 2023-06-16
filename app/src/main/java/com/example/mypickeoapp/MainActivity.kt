package com.example.mypickeoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.mypickeoapp.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var questionsList : MaterialListResponse? = null

    companion object{
        var found = 0
        var notfound = 0
        var totalQuestions = 0
    }

    var userName: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.extras!!.getString("username").toString()

        var i=1

        binding.btnNext.setOnClickListener(View.OnClickListener {

            val selectedOption = binding.radioGroup?.checkedRadioButtonId

            if(selectedOption != -1) {

                val radbutton = findViewById<View>(selectedOption!!) as RadioButton

                if(radbutton.text.toString().isNullOrEmpty()){
                    Toast.makeText(this, "Te falto seleccionar una opcion", Toast.LENGTH_SHORT).show()
                } else {
                    update(binding.txIdReg.text.toString(), radbutton.text.toString())
                }

                questionsList.let {

                    if (i < it?.size!!) {

                        totalQuestions = it.size

                        if (radbutton.text.toString().equals("Encontrado")) {
                            found++
                            binding.txtResult?.text = "Materiales Pickeados : $found"
                        }else{
                            notfound++
                            binding.txtNotFound?.text = "Materiales No Encontrados: $notfound"
                        }

                        binding.txIdReg?.text = questionsList!![i].id_pickeo.toString()

                        binding.txtMaterial?.text = "Material:  " + questionsList!![i].material_number
                        binding.txtname?.text = "Nombre:  " + questionsList!![i].Nombre
                        binding.txtrel?.text = "No.Reloj: " + questionsList!![i].employee

                        binding.txtzone?.text = "Zona: " + questionsList!![i].zone_ma
                        binding.txtrack?.text = "Rack: " + questionsList!![i].rack_location
                        binding.txtqty?.text = "Quantity: " + questionsList!![i].box
                        binding.txtpo?.text = "PQ Task: " + questionsList!![i].po_task_id

                        try {

                            var barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                            var bitmap: Bitmap = barcodeEncoder.encodeBitmap(
                                questionsList!![i].po_task_id.toString(), BarcodeFormat.CODE_39,
                                450,
                                110
                            )

                            binding.ivCodigoQR.setImageBitmap(bitmap)

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        binding.txtplant?.text = "Planta: " + questionsList!![i].planta
                        binding.txtrequi?.text = "Requisicion: " + questionsList!![i].requisicion

                        binding.radio1?.text = "Encontrado"
                        binding.radio2?.text = "No encontrado"

                        if (i == it.size.minus(1)) {
                            binding.btnNext?.text = "Finish"
                        }

                        binding.radioGroup?.clearCheck()
                        i++
                    } else {

                        if (radbutton.text.toString().equals("Encontrado")) {
                            found++
                            binding.txtResult?.text = "Correct Answer : $found"
                        }

                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }
            }

            else{

                Toast.makeText(this@MainActivity,"Please select one option",Toast.LENGTH_SHORT).show()

            }
        })



        val quesApi=RetrofitHelper.getInstance().create(EmployeeApi::class.java)

        GlobalScope.launch {
            val result=quesApi.getQues(userName)

            if (result.body().toString().equals("error")){

                Toast.makeText(applicationContext, "No cuentas con material para localizar", Toast.LENGTH_SHORT).show()

            }else{

                Log.e("api response is ", result.body().toString())
                questionsList = result.body()!!
                Log.e("total questions are ",questionsList?.size.toString())

                GlobalScope.launch(Dispatchers.Main) {


                    Log.e("total questions are ", questionsList?.size.toString())

                    try {

                        var barcodeEncoder: BarcodeEncoder = BarcodeEncoder()
                        var bitmap: Bitmap = barcodeEncoder.encodeBitmap(
                            questionsList!![0].po_task_id.toString(), BarcodeFormat.CODE_39,
                            450,
                            110
                        )

                        binding.ivCodigoQR.setImageBitmap(bitmap)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    binding.txIdReg?.text= questionsList!![0].id_pickeo.toString()

                    binding.txtMaterial?.text= "Material: " +questionsList!![0].material_number
                    binding.txtname?.text= "Nombre:  " +questionsList!![0].Nombre
                    binding.txtrel?.text= "No.Reloj:  " +questionsList!![0].employee

                    binding.txtzone?.text = "Zona: " + questionsList!![0].zone_ma
                    binding.txtrack?.text = "Rack: " + questionsList!![0].rack_location
                    binding.txtqty?.text = "Quantity: " + questionsList!![0].box
                    binding.txtpo?.text = "PQ Task: " + questionsList!![0].po_task_id

                    binding.txtplant?.text = "Planta: " + questionsList!![0].planta
                    binding.txtrequi?.text = "Requisicion: " + questionsList!![0].requisicion

                    binding.radio1?.text= "Encontrado"
                    binding.radio2?.text= "No encontrado"

                    binding.txtResult?.text = "Materiales Pickeados : $found"
                    binding.txtNotFound?.text = "Materiales No Encontrados: $notfound"
                }

            }
        }
    }

    private fun update(idReg: String, Status: String) {
        val quesApi = RetrofitHelper.getInstance().create(EmployeeApi::class.java)

        GlobalScope.launch {
            val result = quesApi.updatereg(idReg,Status)
            Log.e("api response is ", result.body().toString())
           GlobalScope.launch(Dispatchers.Main) {
                if (result.body().toString().equals("ok")) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    Toast.makeText(applicationContext, "Se actualizo registro", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(applicationContext, "No se actualizo registro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}