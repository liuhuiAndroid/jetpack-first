package com.jetpack.first.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.jetpack.first.R
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            // 设置动画参数
            val navOption = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }
            // 参数设置
            val bundle = Bundle()
            bundle.putString("name", "TeaOf")
            findNavController().navigate(R.id.loginFragment, bundle, navOption)
        }

        btn_register.setOnClickListener {
            val action = WelcomeFragmentDirections
                .actionWelcomeFragmentToRegisterFragment()
                .setEMAIL("im.lh@hotmail.com")
            findNavController().navigate(action)
        }
    }


}