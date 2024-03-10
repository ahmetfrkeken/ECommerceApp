package com.ahmetfarukeken.ecommerceapp.util

import android.content.Context
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

class Tools {
    companion object {
        private fun loadJSONFromAsset(context: Context): String? {
            val json: String = try {
                val `is` = context.assets.open("Products.json")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, charset("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }

        fun getProductsFromJson(context: Context): List<Product> {
            return AppPrefs.getProducts(context).ifEmpty {
                val gson = Gson()
                val json: String? = loadJSONFromAsset(context)
                val listType: Type = object : TypeToken<List<Product>>() {}.type
                val products: List<Product> = gson.fromJson(json, listType)
                AppPrefs.saveProducts(context, products)
                products
            }
        }
    }
}