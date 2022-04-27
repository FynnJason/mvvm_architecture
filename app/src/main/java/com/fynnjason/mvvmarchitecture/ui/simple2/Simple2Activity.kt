package com.fynnjason.mvvmarchitecture.ui.simple2

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple2Binding

/**
 * 示例：
 * 网络请求+RecyclerView+下拉刷新+上拉加载
 */
class Simple2Activity : BaseActivity<ActivitySimple2Binding, Simple2ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple2Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple2

    override fun initView() {
        binding.rvSimple2.layoutManager = LinearLayoutManager(this)
        binding.rvSimple2.adapter = viewModel.simple2Adapter
    }

    override fun initListener() {
        // 下拉刷新
        binding.refreshSimple2.setOnRefreshListener {
            viewModel.getSimple2Data()
        }

        // 上拉加载
        viewModel.simple2Adapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.getSimple2Data(true)
        }

        // 监听下拉刷新是否完成
        viewModel.refreshFinish.observe(this){
            if (it){
                binding.refreshSimple2.finishRefresh()
            }
        }
    }

    override fun loadData() {
        viewModel.getSimple2Data()
    }

    override fun eventBus(code: Int, msg: Any) {

    }
}