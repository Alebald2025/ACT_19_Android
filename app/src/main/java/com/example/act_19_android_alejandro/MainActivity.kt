package com.example.act_19_android_alejandro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.act_19_android_alejandro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAct1.setOnClickListener {
            startActivity(Intent(this, Act1Activity::class.java))
        }

        binding.btnAct2.setOnClickListener {
            startActivity(Intent(this, Act2Activity::class.java))
        }
    }
}