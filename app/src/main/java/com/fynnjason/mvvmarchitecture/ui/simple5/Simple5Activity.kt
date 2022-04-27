package com.fynnjason.mvvmarchitecture.ui.simple5

import android.content.Context
import android.content.Intent
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseToolbarActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple5Binding

/**
 * 示例：
 * 带标题和返回键的Activity
 */
class Simple5Activity : BaseToolbarActivity<ActivitySimple5Binding, Simple5ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple5Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple5

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun loadData() {

    }

    override fun eventBus(code: Int, msg: Any) {

    }

    override fun onBack() {
        finish()
    }

    override fun title(): String = "标题"
}