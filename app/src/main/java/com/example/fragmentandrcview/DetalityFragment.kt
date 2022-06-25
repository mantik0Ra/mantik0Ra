package com.example.fragmentandrcview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class DetalityFragment : Fragment(R.layout.fragment_detality) {

    var listOfCharacters = listOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        return inflater.inflate(R.layout.fragment_detality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var textName = view.findViewById<TextView>(R.id.textName)
        var textStatus = view.findViewById<TextView>(R.id.textStatus)
        var textGender = view.findViewById<TextView>(R.id.textGender)

        val VM = ViewModelProvider(this)[RcViewViewModel::class.java]

        var position = DetalityFragmentArgs.fromBundle(requireArguments()).position
        var countPage = DetalityFragmentArgs.fromBundle(requireArguments()).countPage
        var countPosition = DetalityFragmentArgs.fromBundle(requireArguments()).countPosition
        VM.getCharacters(countPage.toString())
        position -= countPosition
        Log.d("TAG", "позиция и страница ${position}, ${countPage}")


        VM.liveCharactersData.observe(this.viewLifecycleOwner) {
            getList(it)
            textName.text = listOfCharacters[position - 1].name
            textStatus.text = listOfCharacters[position - 1].status
            textGender.text = listOfCharacters[position - 1].gender
            Log.d("TAG", "${listOfCharacters.size}")
        }

    }
    fun getList(characters: List<Result>) : List<Result> {
        listOfCharacters = characters
        Log.d("TAG", "размер массива ${ listOfCharacters.size}")
        return listOfCharacters
    }

}