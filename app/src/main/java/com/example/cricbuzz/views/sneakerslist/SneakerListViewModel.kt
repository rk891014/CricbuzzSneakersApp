package com.example.cricbuzz.views.sneakerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.repository.SneakersRepository
import com.example.cricbuzz.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerListViewModel @Inject constructor(
    private val sneakersRepository: SneakersRepository
) : ViewModel() {

    private var allSneakersLst : List<Sneaker> = emptyList()
    private val _sneakerList: MutableLiveData<List<Sneaker>> = MutableLiveData()
    val sneakerList : LiveData<List<Sneaker>> = _sneakerList

    private val _editTextVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val editTextVisibility : LiveData<Boolean> = _editTextVisibility

    private val _addCart : MutableLiveData<Event<String>> = MutableLiveData()
    val addCart : LiveData<Event<String>> = _addCart
    init {
        getSneakerList()
    }

    fun getSneakerList() {
        viewModelScope.launch(Dispatchers.IO) {
            allSneakersLst = sneakersRepository.getSneakers()
            _sneakerList.postValue(allSneakersLst)
        }
    }

    fun addToCart(sneaker: Sneaker) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = sneakersRepository.addToCart(sneaker)
            _addCart.postValue(Event(res))
            getSneakerList()
        }
    }

    fun setSearchEditTextVisible(visible : Boolean){
        _editTextVisibility.postValue(visible)
    }
    fun onUsernameTextChanged(text: CharSequence?) {
        val filteredList = allSneakersLst.filter {
            it.sneakerName.lowercase().contains(text.toString())
        }
        _sneakerList.postValue(if(text.toString() == "") allSneakersLst else filteredList)
    }
}