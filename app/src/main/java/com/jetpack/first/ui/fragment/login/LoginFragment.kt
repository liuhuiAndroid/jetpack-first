package com.jetpack.first.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jetpack.first.databinding.FragmentLoginBinding
import com.jetpack.first.viewmodels.LoginModel
import com.jetpack.first.viewmodels.factory.LoginModelFactory

class LoginFragment : Fragment() {

    // Obtain ViewModel
    private val loginModel: LoginModel by viewModels { LoginModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
//            inflater,
//            R.layout.fragment_login,
//            container,
//            false
//        )
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = loginModel
        binding.activity = activity
        return binding.root
    }

}