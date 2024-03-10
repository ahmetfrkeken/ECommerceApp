package com.ahmetfarukeken.ecommerceapp.ui.fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetfarukeken.ecommerceapp.R
import com.ahmetfarukeken.ecommerceapp.databinding.FragmentProductListBinding
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.adapter.OnRecyclerViewRowClickListener
import com.ahmetfarukeken.ecommerceapp.ui.adapter.ProductListAdapter
import com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel.ProductListViewModel

class ProductListFragment : Fragment(), OnRecyclerViewRowClickListener {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel
    private var navController: NavController? = null

    private val productRVAdapter = ProductListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initView()
        initViewModel()
        observeLiveData()
        getProductData()
        return binding.root
    }

    private fun initView() {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        navController = NavHostFragment.findNavController(this@ProductListFragment)
        binding.openBagIB.setOnClickListener{
            navController?.navigate(R.id.action_productListFragment_to_bagFragment)
        }
        initRV()
    }

    private fun initRV() {
        val rvLLM = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.productRV.apply {
            layoutManager = rvLLM
            adapter = productRVAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    private fun observeLiveData() {
        viewModel.getProducts().observe(requireActivity()) { products ->
            products?.let {
                updateViewData(products = it)
            }
        }
    }

    private fun getProductData() {
        viewModel.getProductListFromJson(requireContext())
    }

    private fun updateViewData(products: List<Product>?) {
        binding.apply {
            productRVAdapter.updateProductList(products)
        }
    }

    override fun onRowClick(indexOfProduct: Int) {
        val bundle = Bundle()
        bundle.putInt("indexOfProduct", indexOfProduct);
        navController?.navigate(R.id.action_productListFragment_to_productDetailFragment, bundle)
    }
}
