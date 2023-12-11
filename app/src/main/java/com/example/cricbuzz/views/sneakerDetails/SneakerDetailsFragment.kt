package com.example.cricbuzz.views.sneakerDetails

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentSneakerDetailsBinding
import com.example.cricbuzz.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerDetailsFragment : Fragment() {
    private val viewModel: SneakerDetailViewModel by viewModels()
    private val args: SneakerDetailsFragmentArgs by navArgs()

    private var _binding: FragmentSneakerDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sneaker_details, container, false
        )
        binding.sneaker = args.sneaker
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setObservers()
    }

    private fun setObservers() {

        viewModel.addCart.observe(viewLifecycleOwner) {event->
            event.getContentIfNotHandled()?.let { str ->
                showSnackBar(binding.root, str)
            }
            binding.addCart.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.green_color))
        }
    }

    private fun setClickListeners() {
        binding.addCart.setOnClickListener {
            viewModel.addToCart(args.sneaker)
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cartImage.setOnClickListener {
            findNavController().navigate(SneakerDetailsFragmentDirections.actionGlobalToSneakerCart())
        }
    }


}