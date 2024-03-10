package com.ahmetfarukeken.ecommerceapp.ui.fragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetfarukeken.ecommerceapp.R
import com.ahmetfarukeken.ecommerceapp.databinding.FragmentBagBinding
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.adapter.BagListAdapter
import com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel.BagViewModel
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class BagFragment : Fragment() {
    private lateinit var binding: FragmentBagBinding
    private lateinit var viewModel: BagViewModel
    private lateinit var products: List<Product>
    private var amount: Int = 0
    private var price: Double = 0.0
    private val bagRVAdapter = BagListAdapter(arrayListOf())

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        getProducts()
        initView()
        initRV()
        observeLiveData()
        return binding.root
    }


    private fun initView() {
        binding = FragmentBagBinding.inflate(layoutInflater)
        navController = NavHostFragment.findNavController(this@BagFragment)
        binding.apply {
            checkoutB.setOnClickListener {
                clearBag()

                val bundle = Bundle()
                bundle.putInt("amount", amount)
                bundle.putDouble("price", price)
                navController?.navigate(R.id.action_bagFragment_to_orderConfirmationFragment, bundle)
            }
            backIB.setOnClickListener {
                navController?.popBackStack()
            }
        }
    }

    private fun initRV() {
        val rvLLM = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.bagRV.apply {
            layoutManager = rvLLM
            adapter = bagRVAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[BagViewModel::class.java]
    }

    private fun getProducts() {
        viewModel.getProductsFromPreferences(requireContext())
    }

    private fun observeLiveData() {
        viewModel.getProducts().observe(requireActivity()) { products ->
            products?.let {
                this.products = products
                bagRVAdapter.updateProductList(products)
            }
        }
        viewModel.getProductsTotalAmount().observe(requireActivity()) { amount ->
            amount?.let {
                this.amount = amount
                binding.totalItemCount = amount
            }
        }
        viewModel.getProductsTotalPrice().observe(requireActivity()) { price ->
            price?.let {
                this.price = price
                binding.totalPrice = price
            }
        }
    }

    private fun clearBag() {
        for (product in products) {
            product.isAddedToBag = false
            AppPrefs.saveProducts(requireContext(), products)
        }
    }
}