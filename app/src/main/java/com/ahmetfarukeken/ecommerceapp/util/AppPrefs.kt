package com.ahmetfarukeken.ecommerceapp.util

import android.content.Context
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppPrefs {
    companion object {
        private val APP_PREFS = "APP_PREFS"
        private val PRODUCTS = "PRODUCTS"

        fun saveProducts(context: Context, products: List<Product>) {
            val gson = Gson()
            val prefs = context.getSharedPreferences(
                APP_PREFS,
                Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            val json = gson.toJson(products)
            editor.putString(
                PRODUCTS,
                json
            )
            editor.apply()
        }

        fun getProducts(context: Context): List<Product> {
            val preferences = context.getSharedPreferences(
                APP_PREFS,
                Context.MODE_PRIVATE
            )
            val json = preferences.getString(PRODUCTS, "")
            var products: List<Product> = ArrayList()
            val gson = Gson()
            if (json!!.isNotEmpty()) {
                val listType = object : TypeToken<List<Product>>() {}.type
                products = gson.fromJson(json, listType)
            }
            return products
        }

    }
}