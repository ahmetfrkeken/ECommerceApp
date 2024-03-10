package com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.BaseViewModel
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class OrderConfirmationViewModel: BaseViewModel() {
    private val productsTotalPrice = MutableLiveData<Double>()
    private val productsTotalAmount = MutableLiveData<Int>()

    fun getProductsTotalPrice(): LiveData<Double> {
        return productsTotalPrice
    }

    fun getProductsTotalAmount(): LiveData<Int> {
        return productsTotalAmount
    }

    fun getChartInfos(bundle: Bundle?) {
        val amount = bundle?.getInt("amount") ?: 0
        productsTotalAmount.postValue(amount)
        val price = bundle?.getDouble("price") ?: 0.0
        productsTotalPrice.postValue(price)
    }
}