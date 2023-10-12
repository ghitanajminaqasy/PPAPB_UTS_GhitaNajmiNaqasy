package com.example.dietapp

import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.example.dietapp.databinding.ActivityGetStartedBinding
import java.text.SimpleDateFormat

class GetStartedActivity : AppCompatActivity() {
    private val diet = arrayOf(
        "Pilih Tujuan Diet",
        "Bulking",
        "Cutting",
        "Maintaning"
    )

    private val berat = arrayOf(
        "Satuan berat",
        "Kg",
        "lbs"
    )
    private lateinit var binding:ActivityGetStartedBinding

    private  var selectedDate:String=""

    companion object{
        const val EXTRA_BERAT_TARGET = "extra1"
        const val EXTRA_TANGGAL_TARGET = "extra2"
        const val EXTRA_MAXIMUMKAL = "extra3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ){_, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear+1}/$year"
            }

            btnGetSimpan.setOnClickListener(){
                txtGet10.setText(selectedDate)
            }

            val adapterDiet = ArrayAdapter(this@GetStartedActivity,
                android.R.layout.simple_spinner_item,diet)
            adapterDiet.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerDiet.adapter = adapterDiet

            val adapterBerat = ArrayAdapter(this@GetStartedActivity,
                android.R.layout.simple_spinner_item,berat)
            adapterBerat.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerKg1.adapter = adapterBerat

            val adapterBerat2 = ArrayAdapter(this@GetStartedActivity,
                android.R.layout.simple_spinner_item,berat)
            adapterBerat2.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerKg2.adapter = adapterBerat2


            btnGetHitung.setOnClickListener() {
                val intentToHome1 = Intent(this@GetStartedActivity,HomeActivity::class.java)
                    .apply {
                    putExtra(EXTRA_BERAT_TARGET,editS4.text.toString())
                    putExtra(EXTRA_TANGGAL_TARGET,txtGet10.text.toString())
                    putExtra(EXTRA_MAXIMUMKAL,editS5.text.toString())
                }
                startActivity(intentToHome1)
            }

        }
    }
    }


