package com.example.act_19_android_alejandro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): Response<List<Comment>>
}