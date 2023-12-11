package com.example.cricbuzz.views.sneakerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricbuzz.util.Event
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.repository.SneakersRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SneakerDetailViewModel @Inject constructor(
    private val sneakersRepository: SneakersRepositoryImp
) : ViewModel() {

    private val _addCart : MutableLiveData<Event<String>> = MutableLiveData()
    val addCart : LiveData<Event<String>> = _addCart

    fun addToCart(sneaker : Sneaker){
        viewModelScope.launch(Dispatchers.IO) {
            val res = sneakersRepository.addToCart(sneaker)
            _addCart.postValue(Event(res))
        }
    }

}