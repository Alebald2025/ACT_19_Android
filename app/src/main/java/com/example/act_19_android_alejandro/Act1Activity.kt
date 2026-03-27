package com.example.act_19_android_alejandro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.act_19_android_alejandro.databinding.ActivityAct1Binding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class Act1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAct1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getPosts()
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    val adapter = PostAdapter(posts)
                    binding.recyclerViewPosts.adapter = adapter
                }
            } catch (e: IOException) {
                // Error de connexió
            } catch (e: HttpException) {
                // Error del servidor
            }
        }
    }
}