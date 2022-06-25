package com.example.fragmentandrcview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Adapter
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RcViewFragment : Fragment(R.layout.rc_view_fragment), FragAdapter.ItemClickListener {

    private lateinit var viewModel: RcViewViewModel
    private lateinit var rcView: RecyclerView
    private lateinit var rcAdapter: FragAdapter
    var countPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.rc_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RcViewViewModel::class.java]
        initViews(view)
        viewModel.getCharacters("1")
        observerViews()



    }

    private fun initViews(view: View) {
        rcView = view.findViewById(R.id.FragRcView)
        rcView.layoutManager = LinearLayoutManager(view.context)
    }

    private fun observerViews() {
        viewModel.apply {
            liveCharactersData.observe(this@RcViewFragment.viewLifecycleOwner) {
                rcAdapter = FragAdapter(it, this@RcViewFragment)
                rcView.adapter = rcAdapter
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.item1 -> {
                countPage++
                viewModel.getCharacters(countPage.toString())
                Log.d("TAG", "Item clicked $countPage")
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        Log.d("TAG", "$position позиция")
        val direction = RcViewFragmentDirections.actionRcViewFragmentToDetalityFragment(position)
        findNavController().navigate(direction)
    }

}