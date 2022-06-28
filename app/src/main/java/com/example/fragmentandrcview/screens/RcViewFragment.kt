package com.example.fragmentandrcview.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentandrcview.CheckConnectionInternetLiveData
import com.example.fragmentandrcview.FragAdapter
import com.example.fragmentandrcview.R
import com.example.fragmentandrcview.RcViewViewModel

class RcViewFragment : Fragment(R.layout.rc_view_fragment), FragAdapter.ItemClickListener {

    private lateinit var viewModel: RcViewViewModel
    private lateinit var rcView: RecyclerView
    private lateinit var rcAdapter: FragAdapter
    private lateinit var checkConnectionInternetLiveData : CheckConnectionInternetLiveData
    private lateinit var viewGroupWithInternetConnection: LinearLayout
    private lateinit var viewGroupWithoutInternetConnection: LinearLayout
    private lateinit var menuNextItem: MenuItem
    private lateinit var menuBackItem: MenuItem
    var countPage = 1
    var countPosition = 0

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
        viewGroupWithInternetConnection = view.findViewById(R.id.layoutMainWithConnection)
        viewGroupWithoutInternetConnection = view.findViewById(R.id.layoutWithoutConnection)
        checkNetworkConnection()




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
        menuNextItem = menu.findItem(R.id.itemNext)
        menuBackItem = menu.findItem(R.id.itemBack)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            menuNextItem.itemId -> {
                if(countPage <= 40) {
                    countPage++
                    countPosition += 20
                    viewModel.getCharacters(countPage.toString())
                    Log.d("TAG", "Item clicked $countPage")
                }
                if(countPage == 40) {
                    countPage = 1
                    countPosition = 0
                    viewModel.getCharacters(countPage.toString())
                }
            }
            menuBackItem.itemId -> {
                if(countPage == 1) {
                    countPage = 40
                    countPosition = 780
                    viewModel.getCharacters(countPage.toString())
                }
                if (countPage > 1) {
                    countPage --
                    countPosition -= 20
                    viewModel.getCharacters(countPage.toString())
                }
            }

        }
        ResetMenuButton()


        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        Log.d("TAG", "$position позиция")
        val direction = RcViewFragmentDirections.actionRcViewFragmentToDetalityFragment(position, countPage, countPosition)
        findNavController().navigate(direction)
    }

    private fun checkNetworkConnection() {
        checkConnectionInternetLiveData = CheckConnectionInternetLiveData(requireActivity().application)

        checkConnectionInternetLiveData.observe(this.viewLifecycleOwner) { isConnected ->

            if (isConnected) {
                ResetViewWithInternetConnection()


            }
            if (!isConnected) {
                ResetViewWithoutInternetConnection()
            }
        }
    }
    private fun ResetViewWithInternetConnection() {
        viewGroupWithInternetConnection.visibility = View.VISIBLE
        viewGroupWithoutInternetConnection.visibility = View.GONE
        countPage = 1
        countPosition = 0
    }

    private fun ResetViewWithoutInternetConnection() {
        viewGroupWithInternetConnection.visibility = View.GONE
        viewGroupWithoutInternetConnection.visibility = View.VISIBLE
        countPage = 1
        countPosition = 0
    }
    private fun ResetMenuButton() {
        checkConnectionInternetLiveData = CheckConnectionInternetLiveData(requireActivity().application)

        checkConnectionInternetLiveData.observe(this.viewLifecycleOwner) { isConnected ->

            if(isConnected) {
                menuNextItem.isEnabled = true
                menuBackItem.isEnabled = true
            }
            if(!isConnected) {
                menuNextItem.isEnabled = false
                menuBackItem.isEnabled = false
            }
        }
    }



}