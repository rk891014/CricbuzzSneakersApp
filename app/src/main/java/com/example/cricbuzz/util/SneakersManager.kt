package com.example.cricbuzz.util

import android.content.SharedPreferences
import android.content.res.AssetManager
import android.util.Log
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.domain.SneakerCart
import com.example.cricbuzz.domain.SneakerCollection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object SneakersManager {

    private lateinit var cartDataMap : HashMap<Int,SneakerCart>
    private val gson = Gson()


    fun getSneakersData(assetManager: AssetManager) : List<Sneaker>{
        val jsonString = assetManager.open("SneakersData.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, SneakerCollection::class.java).sneakers
    }

    fun getSneakersCartData(sharedPreferences: SharedPreferences) : List<SneakerCart> {
        initialiseCartMapData(sharedPreferences)
        return cartDataMap.values.toList()
    }


    fun addToCart(sharedPreferences: SharedPreferences, sneaker: Sneaker) : String {
        initialiseCartMapData(sharedPreferences)
        try {
            if(cartDataMap.containsKey(sneaker.id))
                return "AllReady Added"
            else {
                cartDataMap[sneaker.id] = SneakerCart(sneaker, 1)
                sharedPreferences.edit().putString("myCart", gson.toJson(cartDataMap)).apply()
            }
        }catch (e : Exception){
            e.message
            return "Something Went Wrong"
        }
        return "Added SuccessFully"
    }

    private fun initialiseCartMapData(sharedPreferences: SharedPreferences) {
        if(!::cartDataMap.isInitialized){
            val jsonString = sharedPreferences.getString("myCart", "")
            val type = object : TypeToken<HashMap<Int, SneakerCart>>() {}.type
            cartDataMap = if(jsonString == "")
                HashMap()
            else
                gson.fromJson(jsonString, type)
        }
    }

    fun removeFromCart(sharedPreferences: SharedPreferences, sneaker: Sneaker){
        initialiseCartMapData(sharedPreferences)
        if(cartDataMap.containsKey(sneaker.id)){
            cartDataMap.remove(sneaker.id)
            sharedPreferences.edit().putString("myCart", gson.toJson(cartDataMap)).apply()
        }
    }

    fun updateCart(sharedPreferences: SharedPreferences, sneakerInfo: SneakerCart, itemCount: Int) {
        initialiseCartMapData(sharedPreferences)
        if(cartDataMap.containsKey(sneakerInfo.sneaker.id)){
            if(itemCount <= 0)
                removeFromCart(sharedPreferences,sneakerInfo.sneaker)
            else
                cartDataMap[sneakerInfo.sneaker.id]?.itemCount = itemCount

            sharedPreferences.edit().putString("myCart", gson.toJson(cartDataMap)).apply()
        }
    }
}