package com.example.dietapp

import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dietapp.GetStartedActivity.Companion.EXTRA_BERAT_TARGET
import com.example.dietapp.GetStartedActivity.Companion.EXTRA_MAXIMUMKAL
import com.example.dietapp.GetStartedActivity.Companion.EXTRA_TANGGAL_TARGET
import com.example.dietapp.InputDataActivity.Companion.EXTRA_BREAKFAST
import com.example.dietapp.InputDataActivity.Companion.EXTRA_DINNER
import com.example.dietapp.InputDataActivity.Companion.EXTRA_DURASI_WORKOUT
import com.example.dietapp.InputDataActivity.Companion.EXTRA_KALORIMAKAN
import com.example.dietapp.InputDataActivity.Companion.EXTRA_KALORIWORKOUT
import com.example.dietapp.InputDataActivity.Companion.EXTRA_LUNCH
import com.example.dietapp.InputDataActivity.Companion.EXTRA_NAMA_WORKOUT
import com.example.dietapp.InputDataActivity.Companion.EXTRA_SELECTED_TIME
import com.example.dietapp.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var waktuHariIni: String? = null
    private var kaloriMakan: String? = null
    private var kaloriDataWorkout: String? = null
    private var maximum_kalori: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        maximum_kalori = intent.getStringExtra(EXTRA_MAXIMUMKAL)
        val tanggal_target = intent.getStringExtra(EXTRA_TANGGAL_TARGET)
        val berat_target = intent.getStringExtra(EXTRA_BERAT_TARGET)


        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        waktuHariIni = currentDate

        val tanggal_target_diet = dateFormat.parse(tanggal_target)
        val current_tanggal_Diet = dateFormat.parse(currentDate)



        with(binding){
            txtHome32.text = tanggal_target
            txtHome31.text = berat_target
            txtHome35.text = maximum_kalori
            txtHome1.text = waktuHariIni



            btnHomeinput.setOnClickListener {
                val intentToDataActivity = Intent(this@HomeActivity, InputDataActivity::class.java)
                launcher.launch(intentToDataActivity)
            }
        }

    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result->
        if (result.resultCode == Activity.RESULT_OK){
            val data = result.data
            kaloriMakan = data?.getStringExtra(EXTRA_KALORIMAKAN)
            kaloriDataWorkout = data?.getStringExtra(EXTRA_KALORIWORKOUT)
            val breakfast_diet = data?.getStringExtra(EXTRA_BREAKFAST)
            val lunch_diet = data?.getStringExtra(EXTRA_LUNCH)
            val dinner_diet = data?.getStringExtra(EXTRA_DINNER)
            val kalori_workout_diet = data?.getStringExtra(EXTRA_KALORIWORKOUT)
            val nama_workout_diet = data?.getStringExtra(EXTRA_NAMA_WORKOUT)
            val durasi_workout_diet = data?.getStringExtra(EXTRA_DURASI_WORKOUT)
            val time_makan_diet = data?.getStringExtra(EXTRA_SELECTED_TIME)

            with(binding){
                txtHome7.text = breakfast_diet
                txtHome9.text = lunch_diet
                txtHome11.text = dinner_diet
                txtHome17.text = nama_workout_diet
                txtHome18.text = kalori_workout_diet
                txtHome30.text = durasi_workout_diet
                txtHome23.text = time_makan_diet
            }
        }
    }

}