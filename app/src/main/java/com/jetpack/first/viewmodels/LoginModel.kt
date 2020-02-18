package com.jetpack.first.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetpack.first.common.Constants
import com.jetpack.first.db.data.User
import timber.log.Timber

class LoginModel constructor(name: String, password: String): ViewModel() {

    // ObservableField是一个可观察的域
    val nameField = ObservableField<String>(name)
    val passwordField = ObservableField<String>(password)

    /**W
     * 用户名改变回调的函数
     */
    fun onNameChanged(s: CharSequence) {
        nameField.set(s.toString())
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        passwordField.set(s.toString())
    }

    fun login() {
        Timber.i("nameField.get(): ${nameField.get()}")
        Timber.i("passwordField.get(): ${passwordField.get()}")
        if (nameField.get().equals(Constants.USER_NAME) && passwordField.get().equals(Constants.USER_PWD)) {
//            Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, MainActivity::class.java)
//            context.startActivity(intent)
        }
    }

}
