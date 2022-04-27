package com.fynnjason.mvvmarchitecture.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivityMainBinding
import com.fynnjason.mvvmarchitecture.ui.simple1.Simple1Activity
import com.fynnjason.mvvmarchitecture.ui.simple2.Simple2Activity
import com.fynnjason.mvvmarchitecture.ui.simple3.Simple3Activity
import com.fynnjason.mvvmarchitecture.ui.simple4.Simple4Activity
import com.fynnjason.mvvmarchitecture.ui.simple5.Simple5Activity
import com.fynnjason.mvvmarchitecture.ui.simple6.Simple6Activity
import com.fynnjason.mvvmarchitecture.ui.simple7.Simple7Activity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun layoutResID(): Int = R.layout.activity_main

    override fun initView() {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = viewModel.mainAdapter
    }

    override fun initListener() {
        viewModel.mainAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    Simple1Activity.newInstance(this)
                }
                1 -> {
                    Simple2Activity.newInstance(this)
                }
                2 -> {
                    Simple3Activity.newInstance(this)
                }
                3 -> {
                    Simple4Activity.newInstance(this)
                }
                4->{
                    Simple5Activity.newInstance(this)
                }
                5->{
                    Simple6Activity.newInstance(this)
                }
                6->{
                    Simple7Activity.newInstance(this)
                }
            }
        }
    }

    override fun loadData() {
        viewModel.getMainData()
    }

    override fun eventBus(code: Int, msg: Any) {

    }
}