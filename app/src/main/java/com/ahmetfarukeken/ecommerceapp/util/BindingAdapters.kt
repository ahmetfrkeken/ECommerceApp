package com.ahmetfarukeken.ecommerceapp.util

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ahmetfarukeken.ecommerceapp.R
import com.ahmetfarukeken.ecommerceapp.constants.ClothesNameEnum

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, imageName: String) {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                ClothesNameEnum.getClothesName(imageName).resId
            )
        )
    }


    @JvmStatic
    @BindingAdapter("setFavIcon")
    fun setFavIcon(imageButton: ImageButton, isLiked: Boolean) {
        if (isLiked) {
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(
                    imageButton.context,
                    R.drawable.ic_filled_favorite
                )
            )
        } else {
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(
                    imageButton.context,
                    R.drawable.ic_favorite_24
                )
            )
        }
    }

    @JvmStatic
    @BindingAdapter("isAddedToBag")
    fun isAddedToBag(button: Button, isAdded: Boolean) {
        button.isEnabled = !isAdded
        button.text = if (isAdded) {
            ContextCompat.getString(button.context, R.string.addedToBag)
        } else {
            ContextCompat.getString(button.context, R.string.addToBag)
        }
    }
}