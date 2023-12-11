package com.example.cricbuzz.repository

import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.domain.SneakerCart

interface SneakersRepository {

    suspend fun getSneakers(): List<Sneaker>

    suspend fun addToCart(sneaker: Sneaker) : String

    suspend fun getCartData(): List<SneakerCart>

    suspend fun removeFromCart(sneaker: Sneaker)

    suspend fun updateCart(sneakerInfo: SneakerCart, itemCount: Int)

}