package com.jetpack.first.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import com.jetpack.first.ui.activity.MainActivity
import com.jetpack.first.common.Constants

class LoginModel constructor(name: String, pwd: String, context: Context) {

    // ObservableField是一个可观察的域
    val nameField = ObservableField<String>(name)
    val pwdField = ObservableField<String>(pwd)
    var context: Context = context

    /**
     * 用户名改变回调的函数
     */
    fun onNameChanged(s: CharSequence) {
        nameField.set(s.toString())
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        pwdField.set(s.toString())
    }

    fun login() {
        if (nameField.get().equals(Constants.USER_NAME) && pwdField.get().equals(Constants.USER_PWD)) {
            Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
