package com.example.act_19_android_alejandro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.act_19_android_alejandro.databinding.ActivityAct1Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Act1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAct1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    val adapter = PostAdapter(posts)
                    binding.recyclerViewPosts.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}