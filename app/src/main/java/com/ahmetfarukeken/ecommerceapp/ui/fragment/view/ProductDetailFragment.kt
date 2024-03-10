package com.ahmetfarukeken.ecommerceapp.ui.fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ahmetfarukeken.ecommerceapp.databinding.FragmentProductDetailBinding
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel.ProductDetailViewModel
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var products: List<Product>
    private var indexOfProduct: Int? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        getIndexOfProduct(arguments)
        initView()
        observeLiveData()
        return binding.root
    }

    private fun initView() {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        products = AppPrefs.getProducts(requireContext())
        navController = NavHostFragment.findNavController(this@ProductDetailFragment)
        binding.apply {
            likeIB.setOnClickListener{
                products[indexOfProduct!!].isLiked = !products[indexOfProduct!!].isLiked
                AppPrefs.saveProducts(requireContext(), products)
                data = products[indexOfProduct!!]
            }
            addToBagB.setOnClickListener{
                products[indexOfProduct!!].isAddedToBag = !products[indexOfProduct!!].isAddedToBag
                AppPrefs.saveProducts(requireContext(), products)
                data = products[indexOfProduct!!]
            }
            backIB.setOnClickListener {
            navController?.popBackStack()}
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]
    }

    private fun getIndexOfProduct(bundle: Bundle?) {
        viewModel.getProductFromPrevScreen(bundle)
    }

    private fun observeLiveData() {
        viewModel.getIndexOfProduct().observe(requireActivity()) { indexOfProduct ->
            indexOfProduct?.let {
                this.indexOfProduct = indexOfProduct
                binding.data = products[indexOfProduct]
            }
        }
    }
}