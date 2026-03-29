package com.example.act_19_android_alejandro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.act_19_android_alejandro.databinding.ActivityAct2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Act2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAct2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewComments.layoutManager = LinearLayoutManager(this)

        binding.btnLoad.setOnClickListener {
            val idText = binding.etPostId.text.toString().trim()
            if (idText.isNotEmpty()) {
                try {
                    val postId = idText.toInt()
                    if (postId in 1..100) {
                        loadPostAndComments(postId)
                    }
                } catch (e: Exception) {
                    // ID no vàlid
                }
            }
        }
    }

    private fun loadPostAndComments(postId: Int) {

        // Carregar Post
        RetrofitClient.instance.getPostById(postId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title ?: "Sense títol"
                    binding.tvPostBody.text = post?.body ?: "Sense contingut"
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // Carregar Comentaris
        RetrofitClient.instance.getComments(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()
                    val adapter = CommentAdapter(comments)
                    binding.recyclerViewComments.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}