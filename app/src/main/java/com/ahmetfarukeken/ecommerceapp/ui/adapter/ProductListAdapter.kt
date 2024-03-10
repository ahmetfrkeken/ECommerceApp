package com.ahmetfarukeken.ecommerceapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmetfarukeken.ecommerceapp.databinding.ItemProductBinding
import com.ahmetfarukeken.ecommerceapp.model.Product
import com.ahmetfarukeken.ecommerceapp.util.AppPrefs

class ProductListAdapter(
    private val productList: ArrayList<Product>,
    private val onRecyclerViewRowClickListener: OnRecyclerViewRowClickListener
) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(getView(parent), onRecyclerViewRowClickListener)
    }

    private fun getView(parent: ViewGroup): ItemProductBinding {
        context = parent.context
        return ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindHolder(productList[position])
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
        private val binding: ItemProductBinding,
        private val onRecyclerViewRowClickListener: OnRecyclerViewRowClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bindHolder(product: Product) {
            binding.apply {
                this.data = product
                root.setOnClickListener(this@ProductViewHolder)
                likeIB.setOnClickListener {
                    product.isLiked = !product.isLiked
                    AppPrefs.saveProducts(context, productList)
                    notifyDataSetChanged()
                }
                addToBagB.setOnClickListener {
                    product.isAddedToBag = true
                    AppPrefs.saveProducts(context, productList)
                    notifyDataSetChanged()
                }
            }
        }

        override fun onClick(v: View?) {
            onRecyclerViewRowClickListener.onRowClick(adapterPosition)
        }
    }
}