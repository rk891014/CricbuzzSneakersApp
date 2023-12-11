package com.example.cricbuzz.util

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.setupOnBackPressedCallback(block: () -> Unit): OnBackPressedCallback {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = block.invoke()
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    return callback
}

fun Fragment.showSnackBar(view : View, msg : String){
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
}