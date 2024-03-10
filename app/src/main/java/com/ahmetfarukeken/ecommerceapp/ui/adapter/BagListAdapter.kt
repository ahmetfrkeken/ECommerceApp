package com.ahmetfarukeken.ecommerceapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmetfarukeken.ecommerceapp.databinding.ItemEmptyBinding
import com.ahmetfarukeken.ecommerceapp.databinding.ItemProductOfBagBinding
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class BagListAdapter(
    private val productList: ArrayList<Product>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private val EMPTY = 101
    private val DEFAULT = 102

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DEFAULT) ProductViewHolder(getProductView(parent)) else EmptyViewHolder(getEmptyView(parent))
    }

    private fun getProductView(parent: ViewGroup): ItemProductOfBagBinding {
        context = parent.context
        return ItemProductOfBagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun getEmptyView(parent: ViewGroup): ItemEmptyBinding {
        context = parent.context
        return ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (productList[position].isAddedToBag) {
            DEFAULT
        } else {
            EMPTY
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (productList[position].isAddedToBag)
            (holder as ProductViewHolder).bindHolder(productList[position])
    }

    fun updateProductList(newProductList: List<Product>?) {
        productList.clear()
        if (newProductList != null) {
            productList.addAll(newProductList)
        }
        notifyDataSetChanged()
    }

    fun addProductList(newProductList: List<Product>?) {
        if (newProductList != null) {
            productList.addAll(newProductList)
        }
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(
        private val binding: ItemProductOfBagBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(product: Product) {
            binding.apply {
                this.data = product
                removeFromBagB.setOnClickListener {
                    product.isAddedToBag = false
                    AppPrefs.saveProducts(context, productList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    class EmptyViewHolder(binding: ItemEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}