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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        return inflater.inflate(R.layout.fragment_detality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var text = view.findViewById<TextView>(R.id.textView)
        var listOfDetails = listOf<Result>()
        val VM = ViewModelProvider(this)[RcViewViewModel::class.java]
        VM.getDetails(DetalityFragmentArgs.fromBundle(requireArguments()).position)
        VM.liveCharacterDetails.observe(this.viewLifecycleOwner) {
            listOfDetails = it
            text.text = listOfDetails
        }

    }

}