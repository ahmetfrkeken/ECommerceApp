package com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.BaseViewModel
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class BagViewModel: BaseViewModel() {
    private val products = MutableLiveData<List<Product>>()
    private val productsTotalPrice = MutableLiveData<Double>()
    private val productsTotalAmount = MutableLiveData<Int>()

    fun getProducts(): LiveData<List<Product>> {
        return products
    }
    fun getProductsTotalPrice(): LiveData<Double> {
        return productsTotalPrice
    }
    fun getProductsTotalAmount(): LiveData<Int> {
        return productsTotalAmount
    }

    fun getProductsFromPreferences(context: Context) {
        var amount = 0
        var price = 0.0
        for (product in AppPrefs.getProducts(context)){
            if (product.isAddedToBag){
                amount++
                price += product.price
            }
        }
        productsTotalAmount.postValue(amount)
        productsTotalPrice.postValue(price)
        products.postValue(AppPrefs.getProducts(context))
    }
}