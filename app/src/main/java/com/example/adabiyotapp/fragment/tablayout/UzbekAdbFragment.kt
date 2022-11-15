package com.example.adabiyotapp.fragment.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.adabiyotapp.adapter.AdibAdapter
import com.example.adabiyotapp.databinding.FragmentUzbekadbBinding

class UzbekAdbFragment : Fragment() {

    private var _binding: FragmentUzbekadbBinding? = null
    private val binding get() = _binding!!
    private val userAdapter by lazy { AdibAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUzbekadbBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView2.apply {
            adapter = userAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}