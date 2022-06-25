package com.example.fragmentandrcview

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("character/")
    fun getCharacters(@Query("page") pageId : String) : Call<Characters>

    @GET("character/{id}")
    fun getDetails(@Path("id") id: Int) : Call<Characters>

    companion object {

        var BASE_URL = "https://rickandmortyapi.com/api/"

        fun create() : API {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create()
        }
    }


}