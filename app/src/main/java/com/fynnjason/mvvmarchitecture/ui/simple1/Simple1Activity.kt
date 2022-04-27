package com.fynnjason.mvvmarchitecture.ui.simple1

import android.content.Context
import android.content.Intent
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple1Binding

/**
 * 示例：
 * 网络请求+TextView
 */
class Simple1Activity : BaseActivity<ActivitySimple1Binding, Simple1ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple1Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple1

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun loadData() {
        viewModel.getSimple1Data()
    }

    override fun eventBus(code: Int, msg: Any) {

    }
}