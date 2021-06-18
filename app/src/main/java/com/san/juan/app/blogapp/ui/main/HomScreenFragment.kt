package com.san.juan.app.blogapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.san.juan.app.blogapp.R
import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.data.remote.home.HomeScreenDataSource
import com.san.juan.app.blogapp.databinding.FragmentHomScreenBinding
import com.san.juan.app.blogapp.domain.home.HomeScreenRepoImpl
import com.san.juan.app.blogapp.presentation.HomeScreenViewModel
import com.san.juan.app.blogapp.presentation.HomeScreenViewModelFactory
import com.san.juan.app.blogapp.ui.main.adapters.HomeScreenAdapter

class HomScreenFragment : Fragment(R.layout.fragment_hom_screen) {
    private lateinit var binding: FragmentHomScreenBinding

    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(HomeScreenRepoImpl(HomeScreenDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomScreenBinding.bind(view)

        viewModel.fetchLatestPost().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvHome.visibility = View.GONE
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(),"An error occurred ${result.exception.message}", Toast.LENGTH_LONG).show()

                }
                is Resource.Success -> {
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                    binding.rvHome.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                }
            }
        })

    }
}