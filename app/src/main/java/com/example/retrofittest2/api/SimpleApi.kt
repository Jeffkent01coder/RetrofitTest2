package com.example.retrofittest2.api

import com.example.retrofittest2.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

//    @Headers(
//        "Authorisation: 12345656",
//        "Platform: Android"
//    )

    @GET("posts/1")
    suspend fun getPost(
        @Header("Auth") auth: String
    ): Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getCustomPosts(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<Post>
}