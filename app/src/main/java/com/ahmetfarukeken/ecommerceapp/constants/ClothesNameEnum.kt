package com.ahmetfarukeken.ecommerceapp.constants

import com.ahmetfarukeken.ecommerceapp.R

enum class ClothesNameEnum(var image_name: String, var resId: Int) {
    long_sleeve_sweater("long-sleeve-sweater", R.drawable.long_sleeve_sweater),
    long_coat("long-coat", R.drawable.long_coat),
    rain_coat("rain-coat", R.drawable.rain_coat),
    printed_midi_dress("printed-midi-dress", R.drawable.printed_midi_dress),
    blazer_coat("blazer-coat", R.drawable.blazer_coat),
    scoop_neck_midi_dress("scoop-neck-midi-dress", R.drawable.scoop_neck_midi_dress),
    short_sleeve_sweater("short-sleeve-sweater", R.drawable.short_sleeve_sweater),
    party_dress("party-dress", R.drawable.party_dress),
    winter_coat("winter-coat", R.drawable.winter_coat),
    casual_dress("casual-dress", R.drawable.casual_dress);

    companion object {
        fun getClothesName(value: String): ClothesNameEnum {
            for (clothes in entries) {
                if (clothes.image_name == value)
                    return clothes
            }
            return long_sleeve_sweater
        }
    }
}