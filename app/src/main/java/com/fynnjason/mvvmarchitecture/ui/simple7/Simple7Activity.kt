package com.fynnjason.mvvmarchitecture.ui.simple7

import android.content.Context
import android.content.Intent
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseToolbarActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple7Binding
import com.fynnjason.mvvmarchitecture.room.Simple7Entity

/**
 * Room数据的使用
 */
class Simple7Activity : BaseToolbarActivity<ActivitySimple7Binding, Simple7ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple7Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple7

    override fun initView() {
        viewModel.showContent()
    }

    override fun initListener() {
        binding.btnInsert.setOnClickListener {
            val entity = Simple7Entity("hello", "你好")
            val entity2 = Simple7Entity("bye", "再见")
            viewModel.simple7EntityDao.insertEntities(entity, entity2)
            viewModel.showContent()
        }

        binding.btnUpdate.setOnClickListener {
            val entity = Simple7Entity("go","走")
            entity.id = 1
            viewModel.simple7EntityDao.updateEntities(entity)
            viewModel.showContent()
        }

        binding.btnDelete.setOnClickListener {
            viewModel.simple7EntityDao.deleteAllEntities()
            viewModel.showContent()
        }

        binding.btnQuery.setOnClickListener {
            viewModel.content.value = viewModel.simple7EntityDao.getEntity("你好").id.toString()
        }
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