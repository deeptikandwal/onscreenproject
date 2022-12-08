package com.project.onscreen.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.onscreen.databinding.FragmentAnimeBinding
import com.project.onscreen.views.adapter.AnimeAdapter
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import com.project.onscreen.views.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeFragment : Fragment() {
    lateinit var fragmentAnimeBinding: FragmentAnimeBinding
    val animeViewModel: AnimeViewModel by viewModels()
    val adapter = AnimeAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAnimeBinding = FragmentAnimeBinding.inflate(layoutInflater, null, false)
        val view = fragmentAnimeBinding.root
        setViews()
        setObserver()
        return view
    }

    private fun setObserver() {
        animeViewModel.title.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.clearItems()
                animeViewModel.intentOnScreen.send(OnScreenIntent.FetchAnimes)
            }
        })

        lifecycleScope.launch {
            animeViewModel.state.collect {
                when (it) {
                    is OnScreenState.IDLE -> {
                        //no ops
                    }
                    is OnScreenState.LOADING -> {
                        fragmentAnimeBinding.progress.visibility = View.VISIBLE
                    }

                    is OnScreenState.Animes -> {
                        fragmentAnimeBinding.recyclerView.visibility = View.VISIBLE
                        fragmentAnimeBinding.progress.visibility = View.GONE

                        it.animes.let { it1 -> adapter.addItem(it1) }
                        adapter.notifyDataSetChanged()
                    }
                    is OnScreenState.ERROR -> {
                        fragmentAnimeBinding.progress.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun setViews() {
        fragmentAnimeBinding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        fragmentAnimeBinding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                animeViewModel.title.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
        fragmentAnimeBinding.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    fragmentAnimeBinding.recyclerView.context,
                    (fragmentAnimeBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        fragmentAnimeBinding.recyclerView.adapter = adapter

    }
}