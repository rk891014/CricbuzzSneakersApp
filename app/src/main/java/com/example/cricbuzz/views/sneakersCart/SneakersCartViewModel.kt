package com.example.cricbuzz.views.sneakersCart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.domain.SneakerCart
import com.example.cricbuzz.repository.SneakersRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakersCartViewModel @Inject constructor(
    private val sneakersRepository: SneakersRepositoryImp
) : ViewModel() {

    private val _sneakerCartList: MutableLiveData<List<SneakerCart>> = MutableLiveData()
    val sneakerCartList : LiveData<List<SneakerCart>> = _sneakerCartList

    private val _totalPrice: MutableLiveData<Int> = MutableLiveData()
    val totalPrice : LiveData<Int> = _totalPrice

    init {
        getCartSneakerList()
    }

    private fun getCartSneakerList(){
        viewModelScope.launch(Dispatchers.IO) {
            var sum = 0
            val data = sneakersRepository.getCartData()
            _sneakerCartList.postValue(data)
            data.forEach {
                sum += (it.sneaker.sneakerPrice * it.itemCount)
            }
            _totalPrice.postValue(sum)
        }
    }

    fun removeSneaker(sneaker: Sneaker) {
        viewModelScope.launch(Dispatchers.IO) {
            sneakersRepository.removeFromCart(sneaker)
            getCartSneakerList()
        }
    }

    fun updateSneakerCounter(sneakerInfo: SneakerCart, itemCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sneakersRepository.updateCart(sneakerInfo,itemCount)
            getCartSneakerList()
        }
    }

}