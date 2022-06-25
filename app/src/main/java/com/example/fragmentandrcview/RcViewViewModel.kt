package com.example.fragmentandrcview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RcViewViewModel : ViewModel() {

    var liveCharactersData = MutableLiveData<List<Result>>()

    var liveCharacterDetails = MutableLiveData<List<Result>>()



    fun getCharacters(pageId: String) {
        val retrofit = API.create().getCharacters(pageId)

        retrofit.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                liveCharactersData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.d("TAG", "List is empty")
            }

        })
    }

    fun getDetails(id: Int) {
        val retrofit = API.create().getDetails(id)

        retrofit.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                liveCharacterDetails.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.d("TAG", "List of details is empty")
            }

        })
    }

}