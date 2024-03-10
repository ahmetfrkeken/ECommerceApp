package com.ahmetfarukeken.ecommerceapp.ui.fragment.viewmodel

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.ui.BaseViewModel
import com.ahmetfarukeken.ecommerceapp.util.Tools

class ProductDetailViewModel : BaseViewModel() {
    private val indexOfProduct = MutableLiveData<Int?>()

    fun getIndexOfProduct(): LiveData<Int?> {
        return indexOfProduct
    }

    fun getProductFromPrevScreen(bundle: Bundle?) {
        val index = bundle?.getInt("indexOfProduct")
        if (index != null) {
            indexOfProduct.postValue(index)
        } else {
            indexOfProduct.postValue(null)
        }
    }
}