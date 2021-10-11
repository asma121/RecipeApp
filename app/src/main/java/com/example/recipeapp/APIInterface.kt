package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getUserDetails(): Call<List<RecipeDetails.Datum>>

    @Headers("Content-Type: application/json")
    @POST("/recipes/")
    fun addUserDetails(@Body userdata:RecipeDetails.Datum): Call<RecipeDetails.Datum>


}