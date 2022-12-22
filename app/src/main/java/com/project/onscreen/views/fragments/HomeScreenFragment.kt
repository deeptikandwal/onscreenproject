package com.project.onscreen.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.onscreen.R
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.databinding.FragmentHomeScreenBinding
import com.project.onscreen.domain.model.EmployeeDomainModel
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
    val onScreenAdapter = OnScreenAdapter{ findNavController().navigate(R.id.homescreen_to_detailscreen) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeScreenBinding.inflate(layoutInflater,  container, false).also {
        fragmentHomeScreenBinding=it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setObserver()
    }

    private fun setViews() {
        with(fragmentHomeScreenBinding){
            recycler.run {
                addItemDecoration(DividerItemDecoration(recycler.context, (recycler.layoutManager as LinearLayoutManager).orientation))
                adapter=onScreenAdapter
            }
            back.setOnClickListener {
                activity?.finish()
            }
            fetch.setOnClickListener {
                lifecycleScope.launch {
                    fragmentHomeScreenBinding.recycler.scrollToPosition(0)
                    mhomescreenViewModel.intentOnScreen.send(OnScreenIntent.FetchEmployees)
                }
            }
        }

    }

    private fun setObserver() {
        lifecycleScope.launch {
            mhomescreenViewModel.state.collect {
                modifyUiBasedOnViewState(it)
            }
        }
    }

    private fun modifyUiBasedOnViewState(it: OnScreenState) {
        when (it) {
            is OnScreenState.IDLE -> {
                fragmentHomeScreenBinding.progress.visibility = View.GONE
            }
            is OnScreenState.LOADING -> {
                with(fragmentHomeScreenBinding) {
                    progress.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
            }

            is OnScreenState.SUCCESS -> {
                with(fragmentHomeScreenBinding) {
                    progress.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                }
                updateList(it.user)
            }

            is OnScreenState.ERROR -> {
                fragmentHomeScreenBinding.progress.visibility = View.GONE
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateList(users: List<EmployeeDomainModel>) {
        with(onScreenAdapter){
            setDataList(users)
            notifyDataSetChanged()
        }
    }


}