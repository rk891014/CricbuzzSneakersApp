package com.example.cricbuzz.views.sneakerDetails

import android.util.Log
import androidx.databinding.ObservableParcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.repository.SneakersRepository
import com.example.cricbuzz.repository.SneakersRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SneakerDetailViewModel @Inject constructor(
    private val sneakersRepository: SneakersRepositoryImp
) : ViewModel() {

    fun addToCart(sneaker : Sneaker){
        viewModelScope.launch(Dispatchers.IO) {
            val res = sneakersRepository.addToCart(sneaker)
            Log.e( "adddfgfgdfg",res )
        }
    }

}