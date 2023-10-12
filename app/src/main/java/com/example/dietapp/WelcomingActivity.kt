package com.example.dietapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dietapp.databinding.ActivityWelcomingBinding

class WelcomingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){



            btnToStart.setOnClickListener{
                val intentToGetStarted =
                        Intent(this@WelcomingActivity,GetStartedActivity::class.java)
                startActivity(intentToGetStarted)
            }
        }
    }
}