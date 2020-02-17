package com.jetpack.first.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jetpack.first.ui.activity.MainActivity
import com.jetpack.first.databinding.FragmentLoginBinding
import com.jetpack.first.viewmodels.LoginModel
import com.jetpack.first.viewmodels.LoginXModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.support.v4.startActivity

class LoginFragment : Fragment() {

    private lateinit var loginModel: LoginXModel

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
        loginModel = LoginXModel("", "", requireActivity().application)
        binding.model = loginModel
        binding.activity = activity
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            startActivity<MainActivity>()
        }

    }

}