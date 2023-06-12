package com.example.mypickeoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var txtMaterial:TextView? = null
    var txtName:TextView? = null
    var txtrel:TextView? = null

    var txtzone:TextView? = null
    var txtrack:TextView? = null
    var txtqty:TextView? = null
    var txtpo:TextView? = null
    var txtplant:TextView? = null
    var txtrequi:TextView? = null

    var txtResult: TextView? = null
    var txtNotFound: TextView? = null

    var radioGroup:RadioGroup? = null
    var btnNext:Button? = null

    var rbOption1:RadioButton?=null
    var rbOption2:RadioButton?=null

    var questionsList : MaterialListResponse? = null

    companion object{

        var result = 0
        var notfound = 0
        var totalQuestions = 0

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMaterial=findViewById(R.id.txtMaterial)
        txtName=findViewById(R.id.txtname)
        txtrel=findViewById(R.id.txtrel)

        txtzone=findViewById(R.id.txtzone)
        txtrack=findViewById(R.id.txtrack)
        txtqty=findViewById(R.id.txtqty)
        txtpo=findViewById(R.id.txtpo)
        txtplant=findViewById(R.id.txtplant)
        txtrequi=findViewById(R.id.txtrequi)


        radioGroup = findViewById(R.id.radioGroup)
        btnNext=findViewById(R.id.btnNext)

        rbOption1=findViewById(R.id.radio1)
        rbOption2=findViewById(R.id.radio2)

        txtResult=findViewById(R.id.txtResult)
        txtNotFound=findViewById(R.id.txtNotFound)

        var i=1

        btnNext?.setOnClickListener(View.OnClickListener {

            val selectedOption = radioGroup?.checkedRadioButtonId

            if(selectedOption != -1) {
                val radbutton = findViewById<View>(selectedOption!!) as RadioButton

                questionsList.let {

                    if (i < it?.size!!) {

                        totalQuestions = it.size

                        if (radbutton.text.toString().equals("Encontrado")) {
                            result++
                            txtResult?.text = "Materiales Pickeados : $result"
                        }else{
                            notfound++
                            txtNotFound?.text = "Materiales No Encontrados: $notfound"
                        }

                        txtMaterial?.text = "Material:  " + questionsList!![i].material_number
                        txtName?.text = "Nombre:  " + questionsList!![i].Nombre
                        txtrel?.text = "No.Reloj: " + questionsList!![i].employee

                        txtzone?.text = "Zona: " + questionsList!![i].zone_ma
                        txtrack?.text = "Rack: " + questionsList!![i].rack_location
                        txtqty?.text = "Quantity: " + questionsList!![i].qty_to_pick
                        txtpo?.text = "PO: " + questionsList!![i].po_task_id
                        txtplant?.text = "Planta: " + questionsList!![i].planta
                        txtrequi?.text = "Requisicion: " + questionsList!![i].requisicion

                        rbOption1?.text = "Encontrado"
                        rbOption2?.text = "No encontrado"

                        if (i == it.size.minus(1)) {
                            btnNext?.text = "Finish"
                        }

                        radioGroup?.clearCheck()
                        i++
                    } else {

                        if (radbutton.text.toString().equals("Encontrado")) {
                            result++
                            txtResult?.text = "Correct Answer : $result"
                        }

                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }
            }else{

                Toast.makeText(this@MainActivity,"Please select one option",Toast.LENGTH_SHORT).show()

            }
        })

        val quesApi=RetrofitHelper.getInstance().create(EmployeeApi::class.java)

        GlobalScope.launch {
            val result=quesApi.getQues()

            if (result!=null){


                Log.e("api response is ", result.body().toString())
                questionsList = result.body()!!
                Log.e("total questions are ",questionsList?.size.toString())

                GlobalScope.launch(Dispatchers.Main) {


                    Log.e("total questions are ", questionsList?.size.toString())

                    txtMaterial?.text= "Material: " +questionsList!![0].material_number
                    txtName?.text= "Nombre:  " +questionsList!![0].Nombre
                    txtrel?.text= "No.Reloj:  " +questionsList!![0].employee

                    txtzone?.text = "Zona: " + questionsList!![0].zone_ma
                    txtrack?.text = "Rack: " + questionsList!![0].rack_location
                    txtqty?.text = "Quantity: " + questionsList!![0].qty_to_pick
                    txtpo?.text = "PO: " + questionsList!![0].po_task_id
                    txtplant?.text = "Planta: " + questionsList!![0].planta
                    txtrequi?.text = "Requisicion: " + questionsList!![0].requisicion

                    rbOption1?.text= "Encontrado"
                    rbOption2?.text= "No encontrado"
                }
            }
        }
    }
}