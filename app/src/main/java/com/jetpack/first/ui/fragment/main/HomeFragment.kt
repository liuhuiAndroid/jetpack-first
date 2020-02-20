package com.jetpack.first.ui.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jetpack.first.AppContext
import com.jetpack.first.R
import com.jetpack.first.databinding.FragmentHomeBinding
import com.jetpack.first.db.RepositoryProvider
import com.jetpack.first.ui.adapter.ShoeAdapter
import com.jetpack.first.viewmodels.CustomViewModelProvider
import com.jetpack.first.viewmodels.ShoeModel

class HomeFragment : Fragment() {

    private val viewModel: ShoeModel by viewModels {
        CustomViewModelProvider.providerShoeModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        context ?: return binding.root
        val adapter = ShoeAdapter()
        binding.recyclerView.adapter = adapter
        // 鞋子数据更新的通知
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

}