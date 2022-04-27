package com.fynnjason.mvvmarchitecture.ui.simple3

import android.content.Context
import android.content.Intent
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple3Binding
import com.youth.banner.indicator.CircleIndicator

/**
 * 示例：
 * 网络请求+banner
 */
class Simple3Activity : BaseActivity<ActivitySimple3Binding, Simple3ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple3Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple3

    override fun initView() {
        binding.bannerSimple3.addBannerLifecycleObserver(this)
            .setAdapter(viewModel.bannerAdapter)
            .setIndicator(CircleIndicator(this))
    }

    override fun initListener() {

    }

    override fun loadData() {
        viewModel.getSimple3Data()
    }

    override fun eventBus(code: Int, msg: Any) {

    }
}