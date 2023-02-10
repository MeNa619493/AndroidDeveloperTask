package com.example.androiddevelopertask.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androiddevelopertask.R
import com.example.androiddevelopertask.databinding.FragmentHomeBinding
import com.example.androiddevelopertask.ui.home.HomeViewModel
import com.example.androiddevelopertask.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val mAdapter by lazy { ProductsAdapter(ProductsAdapter.ProductClickListener { product ->
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product))
    }) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getProducts()
        observeProducts()
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvProducts.adapter = mAdapter
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeProducts() {
        showShimmerEffect()
        viewModel.products.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.submitList(it?.products) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun showShimmerEffect() {
        binding.shimmerViewContainer.startShimmer()
    }

    private fun hideShimmerEffect() {
        binding.apply {
            shimmerViewContainer.stopShimmer()
            shimmerViewContainer.hideShimmer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}