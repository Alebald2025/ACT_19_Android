package com.example.act_19_android_alejandro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.act_19_android_alejandro.databinding.ActivityAct2Binding
import kotlinx.coroutines.launch

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
                } catch (e: NumberFormatException) {
                    // ID no vàlid
                }
            }
        }
    }

    private fun loadPostAndComments(postId: Int) {
        lifecycleScope.launch {
            try {
                // Carregar Post
                val postResponse = RetrofitClient.instance.getPostById(postId)
                if (postResponse.isSuccessful) {
                    val post = postResponse.body()
                    binding.tvPostTitle.text = post?.title ?: "Sense títol"
                    binding.tvPostBody.text = post?.body ?: "Sense contingut"
                }

                // Carregar Comentaris
                val commentsResponse = RetrofitClient.instance.getComments(postId)
                if (commentsResponse.isSuccessful) {
                    val comments = commentsResponse.body() ?: emptyList()
                    val adapter = CommentAdapter(comments)
                    binding.recyclerViewComments.adapter = adapter
                }
            } catch (e: Exception) {
                // Error (pots afegir un Toast si vols)
            }
        }
    }
}