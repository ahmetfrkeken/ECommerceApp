package com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetfarukeken.ecommerceapp.ECommerceApplication
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.BaseViewModel
import com.ahmetfarukeken.ecommerceapp.util.Tools
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor() : BaseViewModel() {
    private val products = MutableLiveData<List<Product>>()

    fun getProducts(): LiveData<List<Product>> {
        return products
    }

    fun getProductListFromJson(context: Context){
        products.postValue(Tools.getProductsFromJson(context))
    }
}