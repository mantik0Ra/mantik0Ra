package com.example.fragmentandrcview.model.api

import com.example.fragmentandrcview.data.Characters
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("character/")
    fun getCharacters(@Query("page") pageId : String) : Call<Characters>

    @GET("character/")
    fun getDetails() : Call<Characters>

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