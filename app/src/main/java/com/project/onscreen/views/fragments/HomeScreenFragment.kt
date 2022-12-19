package com.project.onscreen.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.onscreen.R
import com.project.onscreen.data.model.EmployeeList
import com.project.onscreen.databinding.FragmentHomeScreenBinding
import com.project.onscreen.views.adapter.OnScreenAdapter
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import com.project.onscreen.views.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    lateinit var fragmentHomeScreenBinding: FragmentHomeScreenBinding
    val mhomescreenViewModel: HomeScreenViewModel by viewModels()
    val adapter = OnScreenAdapter( arrayListOf(),
        {
        findNavController().navigate(R.id.homescreen_to_detailscreen)
    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeScreenBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home_screen, container, false)
        val view = fragmentHomeScreenBinding.root
        setViews()
        setObserver()
        return view
    }

    private fun setViews() {
        fragmentHomeScreenBinding.recycler.run {
            addItemDecoration(
                DividerItemDecoration(
                    fragmentHomeScreenBinding.recycler.context,
                    (fragmentHomeScreenBinding.recycler.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        fragmentHomeScreenBinding.recycler.adapter = adapter

    }

    private fun setObserver() {
        lifecycleScope.launch {
            mhomescreenViewModel.state.collect {
                when (it) {
                    is OnScreenState.IDLE -> {
                        fragmentHomeScreenBinding.progress.visibility = View.GONE
                    }
                    is OnScreenState.LOADING -> {
                        fragmentHomeScreenBinding.progress.also {
                            it.visibility=View.VISIBLE
                        }
                        fragmentHomeScreenBinding.recycler.visibility = View.GONE

                    }

                    is OnScreenState.SUCCESS -> {
                        fragmentHomeScreenBinding.progress.visibility = View.GONE
                        fragmentHomeScreenBinding.recycler.visibility = View.VISIBLE
                        updateList(it.user as ArrayList<EmployeeList>)
                    }

                    is OnScreenState.ERROR -> {
                        fragmentHomeScreenBinding.progress.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        fragmentHomeScreenBinding.fetch.setOnClickListener {
            lifecycleScope.launch {
                mhomescreenViewModel.intentOnScreen.send(OnScreenIntent.FetchEmployees)
            }
        }
        fragmentHomeScreenBinding.back.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    private fun updateList(users: ArrayList<EmployeeList>) {
        users.let {
            adapter.clearItem()
            adapter.addItem(users)
        }
        adapter.notifyDataSetChanged()
    }


}