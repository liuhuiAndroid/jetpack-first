package com.jetpack.first.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jetpack.first.R
import com.jetpack.first.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 系统会为每个布局文件生成一个绑定类。
        // 默认情况下，类名称基于布局文件的名称，它会转换为 Pascal 大小写形式并在末尾添加 Binding 后缀。
        // 以上布局文件名为 activity_main.xml，因此生成的对应类为 ActivityMainBinding。
        // 此类包含从布局属性（例如，user 变量）到布局视图的所有绑定，并且知道如何为绑定表达式指定值。
        // 建议的绑定创建方法是在扩充布局时创建，如以下示例所示：
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // val activityMainBinding =ActivityMainBinding.inflate(getLayoutInflater())
        val navController = findNavController(R.id.nav_host_fragment)
        // 数据绑定库会针对布局中具有 ID 的每个视图在绑定类中创建不可变字段
        activityMainBinding.navView.setupWithNavController(navController)
    }

}
