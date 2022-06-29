package com.example.fragmentandrcview.model.repository

import com.example.fragmentandrcview.data.Characters
import com.example.fragmentandrcview.model.api.API
import retrofit2.Call

class CharactersDBRepositoryImpl() : CharactersDBRepository {

    private val apiInterface = API.create()

    override fun getCharacters(pageCount: String): Call<Characters> {
        return apiInterface.getCharacters(pageCount)
    }
}