package com.example.cricbuzz.views.sneakerslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentSneakerListBinding
import com.example.cricbuzz.util.setupOnBackPressedCallback
import com.example.cricbuzz.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SneakerListFragment : Fragment() {
    private val viewModel: SneakerListViewModel by viewModels()
    private var onBackPressedCallback: OnBackPressedCallback? = null

    @Inject
    lateinit var adapter: SneakersListAdapter

    private var _binding: FragmentSneakerListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sneaker_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        adapter.clickListener.onItemClick = {
            findNavController().navigate(SneakerListFragmentDirections.actionGlobalToSneakerDetails(it))
        }
        adapter.clickListener.onItemAddCartClick = {
            viewModel.addToCart(it)
        }
        binding.cartImage.setOnClickListener {
            findNavController().navigate(SneakerListFragmentDirections.actionGlobalToSneakerCart())
        }
        onBackPressedCallback = setupOnBackPressedCallback {
            if(binding.searchSneakersEditText.isVisible) {
                binding.searchSneakersEditText.setText("")
                binding.searchSneakersEditText.visibility = View.GONE
                viewModel.getSneakerList()
            }
            else
                requireActivity().finish()
        }
    }

    private fun setObserver() {
        viewModel.sneakerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.addCart.observe(viewLifecycleOwner) {event->
            event.getContentIfNotHandled()?.let { str ->
                showSnackBar(binding.root, str)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSneakerList()
        binding.searchSneakersEditText.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
        onBackPressedCallback = null
    }

}

