package com.example.fragmentandrcview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import kotlin.properties.Delegates


class DetalityFragment : Fragment(R.layout.fragment_detality) {

    var listOfCharacters = listOf<Result>()
    private val VM: RcViewViewModel by viewModels()
    lateinit var textName: TextView
    lateinit var textStatus: TextView
    lateinit var textGender: TextView
    var position by Delegates.notNull<Int>()
    var countPage by Delegates.notNull<Int>()
    var countPosition by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        return inflater.inflate(R.layout.fragment_detality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        takeInfoAboutPositionsOfCharacter()
        VM.getCharacters(countPage.toString())
        observeInfoByLiveDataAboutCharacter()


    }

    private fun initViews(view: View) {
        textName = view.findViewById<TextView>(R.id.textName)
        textStatus = view.findViewById<TextView>(R.id.textStatus)
        textGender = view.findViewById<TextView>(R.id.textGender)
    }

    private fun takeInfoAboutPositionsOfCharacter() {
        position = DetalityFragmentArgs.fromBundle(requireArguments()).position
        countPage = DetalityFragmentArgs.fromBundle(requireArguments()).countPage
        countPosition = DetalityFragmentArgs.fromBundle(requireArguments()).countPosition
        position -= countPosition
        Log.d("TAG", "позиция и страница ${position}, ${countPage}")
    }

    private fun observeInfoByLiveDataAboutCharacter() {
        VM.liveCharactersData.observe(this.viewLifecycleOwner) {
            listOfCharacters = it
            textName.text = listOfCharacters[position - 1].name
            textStatus.text = listOfCharacters[position - 1].status
            textGender.text = listOfCharacters[position - 1].gender
            Log.d("TAG", "${listOfCharacters.size}")
        }
    }

}