package com.example.cricbuzz.repository

import android.content.SharedPreferences
import android.content.res.AssetManager
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.domain.SneakerCart
import com.example.cricbuzz.util.SneakersManager
import javax.inject.Inject

class SneakersRepositoryImp @Inject constructor(
    private val assetManager: AssetManager,
    private val sharedPreferences: SharedPreferences
) : SneakersRepository {

    override suspend fun getSneakers(): List<Sneaker> {
        return SneakersManager.getSneakersData(assetManager = assetManager)
    }

    override suspend fun addToCart(sneaker: Sneaker) : String {
        return SneakersManager.addToCart(sharedPreferences = sharedPreferences,sneaker = sneaker)
    }

    override suspend fun getCartData(): List<SneakerCart> {
        return SneakersManager.getSneakersCartData(sharedPreferences = sharedPreferences)
    }

    override suspend fun removeFromCart(sneaker: Sneaker) {
        SneakersManager.removeFromCart(sharedPreferences = sharedPreferences,sneaker = sneaker)
    }

    override suspend fun updateCart(sneakerInfo: SneakerCart, itemCount: Int) {
        SneakersManager.updateCart(sharedPreferences = sharedPreferences,sneakerInfo = sneakerInfo, itemCount = itemCount)
    }

}