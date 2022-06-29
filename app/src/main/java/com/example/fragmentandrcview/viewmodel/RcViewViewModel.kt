package com.example.fragmentandrcview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentandrcview.model.api.API
import com.example.fragmentandrcview.data.Characters
import com.example.fragmentandrcview.data.Result
import com.example.fragmentandrcview.model.repository.CharactersDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RcViewViewModel : ViewModel() {

    private var _liveCharactersData = MutableLiveData<List<Result>>()
    var liveCharactersData : LiveData<List<Result>> = _liveCharactersData

    private val characterRepository = CharactersDBRepositoryImpl()

    fun getCharacters(pageId: String) {

        val retrofit = characterRepository.getCharacters(pageId)

        retrofit.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                _liveCharactersData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.d("TAG", "List is empty")
            }

        })
    }

}