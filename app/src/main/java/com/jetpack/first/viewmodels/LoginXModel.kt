package com.jetpack.first.viewmodels

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
 * 与 LoginModel 不同的是，将可观察字段替换为 LiveData 对象
 */
class LoginXModel constructor(name: String, pwd: String, application: Application)
    : AndroidViewModel(application) {

    // ObservableField是一个可观察的域
    val nameField = LiveData<String>()
    val pwdField = LiveData<String>(pwd)

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
//        if (nameField.get().equals(Constants.USER_NAME) && pwdField.get().equals(Constants.USER_PWD)) {
//            Toast.makeText(application, "账号密码正确", Toast.LENGTH_SHORT).show()
//            val intent = Intent(application, MainActivity::class.java)
//            application.startActivity(intent)
//        }
    }

}