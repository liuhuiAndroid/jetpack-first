package com.jetpack.first.ui.fragment.main.home

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
import com.jetpack.first.JApplication
import com.jetpack.first.R
import com.jetpack.first.databinding.FragmentHomeBinding
import com.jetpack.first.db.RepositoryProvider
import com.jetpack.first.db.data.User
import com.jetpack.first.ui.adapter.ShoeAdapter
import com.jetpack.first.viewmodel.CustomViewModelProvider
import com.jetpack.first.viewmodel.ShoeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        onSubscribeUi(adapter)
        return binding.root
    }

    /**
     * 鞋子数据更新的通知
     */
    private fun onSubscribeUi(adapter: ShoeAdapter) {
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })

//        Thread{
//            val shoeDao = RepositoryProvider.providerShoeRepository(AppContext)
//            val allShoes = shoeDao.findAllShoe()
//            Log.e("AAA", allShoes.toString())
//        }.start()
    }

}