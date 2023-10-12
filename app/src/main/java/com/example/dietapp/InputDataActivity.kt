package com.example.dietapp

import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import com.example.dietapp.databinding.ActivityHomeBinding
import com.example.dietapp.databinding.ActivityInputDataBinding
import java.text.SimpleDateFormat
import java.util.Locale

class InputDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataBinding
    private var selectedTime: String? = null
    private var waktuInput: String? = null
    private var selectedTimeMakan: String? = null

    companion object{
        const val EXTRA_KALORIMAKAN = "extra1"
        const val EXTRA_KALORIWORKOUT = "extra2"
        const val EXTRA_BREAKFAST = "extra3"
        const val EXTRA_LUNCH = "extra4"
        const val EXTRA_DINNER = "extra5"
        const val EXTRA_NAMA_WORKOUT = "extra6"
        const val EXTRA_DURASI_WORKOUT = "extra7"
        const val EXTRA_SELECTED_TIME = "extra8"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        waktuInput = currentDate


        with(binding){
            timePickerInput.setOnTimeChangedListener{view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                }

            btnInputSave.setOnClickListener{
                Toast.makeText(this@InputDataActivity, "Waktu $waktuInput", Toast.LENGTH_SHORT).show()

                val kaloriWorkoutText = editInput19.text.toString()
                val kaloriBreakfastText = editInput9.text.toString()
                val kaloriLunchText = editInput11.text.toString()
                val kaloriDinnerText = editInput23.text.toString()
                val namaWorkoutText = editInput18.text.toString()
                val durasiWorkoutText = editInput21.text.toString()
                val timeMakanText = selectedTime

                waktuInput = currentDate
                val kaloriBreakfastint = if (kaloriBreakfastText.isNotEmpty()) {
                    kaloriBreakfastText.toInt()
                } else {
                    0
                }

                val kaloriLunchint = if (kaloriLunchText.isNotEmpty()) {
                    kaloriLunchText.toInt()
                } else {
                    0
                }

                val kaloriDinnerint = if (kaloriDinnerText.isNotEmpty()) {
                    kaloriDinnerText.toInt()
                } else {
                    0
                }

                val kaloriWorkoutInt = if (kaloriWorkoutText.isNotEmpty()) {
                    kaloriWorkoutText.toInt()
                } else {
                    0
                }

                val intentToHomeFromInput = Intent(this@InputDataActivity, HomeActivity::class.java)
                    .apply {
                        putExtra(EXTRA_BREAKFAST, kaloriBreakfastint.toString())
                        putExtra(EXTRA_LUNCH, kaloriLunchint.toString())
                        putExtra(EXTRA_DINNER, kaloriDinnerint.toString())
                        putExtra(EXTRA_KALORIWORKOUT,kaloriWorkoutInt.toString())
                        putExtra(EXTRA_NAMA_WORKOUT,namaWorkoutText)
                        putExtra(EXTRA_DURASI_WORKOUT,durasiWorkoutText)
                        putExtra(EXTRA_SELECTED_TIME,timeMakanText)
                    }
                setResult(Activity.RESULT_OK, intentToHomeFromInput)
                finish()
            }

        }

    }

}
