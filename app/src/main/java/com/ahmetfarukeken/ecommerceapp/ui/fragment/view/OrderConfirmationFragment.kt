package com.ahmetfarukeken.ecommerceapp.ui.fragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ahmetfarukeken.ecommerceapp.databinding.FragmentOrderConfirmationBinding
import com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel.OrderConfirmationViewModel

class OrderConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentOrderConfirmationBinding
    private lateinit var viewModel: OrderConfirmationViewModel
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        getChartData(bundle= arguments)
        initView()
        observeLiveData()
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[OrderConfirmationViewModel::class.java]
    }

    private fun initView() {
        binding = FragmentOrderConfirmationBinding.inflate(layoutInflater)
        navController = NavHostFragment.findNavController(this@OrderConfirmationFragment)
        binding.apply {
            closeB.setOnClickListener {
                navController?.graph?.let { it1 -> navController?.navigate(it1.startDestinationId) }
            }
        }
    }

    private fun getChartData(bundle: Bundle?) {
        viewModel.getChartInfos(bundle)
    }

    private fun observeLiveData() {
        viewModel.getProductsTotalAmount().observe(requireActivity()){amount ->
            binding.totalItemCount = amount
        }
        viewModel.getProductsTotalPrice().observe(requireActivity()){price ->
            binding.totalPrice = price
        }
    }
}
