package com.example.cricbuzz.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sneaker(
    @SerializedName("id")
    val id: Int,
    @SerializedName("grid_picture_url")
    val sneakerImage: String = "",
    @SerializedName("name")
    val sneakerName: String = "",
    @SerializedName("retail_price_cents")
    val sneakerPrice: Int,
    @SerializedName("main_picture_url")
    val mainPictureUrl: String,
    @SerializedName("size_range")
    val sizes: List<Double>,
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("release_year")
    val releaseYear: Int,
    @SerializedName("details")
    val sneakerDetails: String,
    @SerializedName("color")
    val color: String,
    var alreadyAddedToCart : Boolean = false
) : Parcelable