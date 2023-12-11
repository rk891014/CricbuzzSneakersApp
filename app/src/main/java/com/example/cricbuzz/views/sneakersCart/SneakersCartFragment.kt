package com.example.cricbuzz.views.sneakersCart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentSneakersCartBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SneakersCartFragment : Fragment() {

    private val viewModel: SneakersCartViewModel by viewModels()

    private var _binding: FragmentSneakersCartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: SneakersCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sneakers_cart, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cartRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sneakerCartList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.price.text = "$ $it"
        }
        adapter.cartClickListener.onItemRemoveCartClick = {
            viewModel.removeSneaker(it.sneaker)
        }
        adapter.cartClickListener.onItemIncrement = {
            viewModel.updateSneakerCounter(it,it.itemCount + 1)
        }
        adapter.cartClickListener.onItemDecrement = {
            viewModel.updateSneakerCounter(it,it.itemCount - 1)
        }
        adapter.cartClickListener.onItemClick = {
            findNavController().navigate(SneakersCartFragmentDirections.actionGlobalToSneakerDetails(it))
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}