package com.example.act_19_android_alejandro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)