package com.example.cricbuzz.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.setupOnBackPressedCallback(block: () -> Unit): OnBackPressedCallback {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = block.invoke()
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    return callback
}