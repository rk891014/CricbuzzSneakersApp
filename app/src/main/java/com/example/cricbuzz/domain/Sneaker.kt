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
    val color: String



//    val box_condition: String,
//    val category: List<String>,
//    val collection_slugs: List<String>,
//    val designer: String,
//    val details: String,
//    val gender: List<String>,
//    val has_picture: Boolean,
//    val has_stock: Boolean,
//    val keywords: List<String>,
//    val midsole: String,
//    val nickname: String,
//    val original_picture_url: String,
//    val product_template_id: Int,
//    val release_date: String,
//    val release_date_unix: Int,
//    val shoe_condition: String,
//    val silhouette: String,
//    val sku: String,
//    val slug: String,
//    val status: String,
//    val story_html: String,
//    val upper_material: String
) : Parcelable