package com.example.act_19_android_alejandro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)