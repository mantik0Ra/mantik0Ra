package com.example.fragmentandrcview.model.repository

import com.example.fragmentandrcview.data.Characters
import retrofit2.Call


interface CharactersDBRepository {

    fun getCharacters(pageCount: String) : Call<Characters>

}