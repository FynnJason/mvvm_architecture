package com.fynnjason.mvvmarchitecture.ui.main

import com.fynnjason.mvvmarchitecture.adapter.MainAdapter
import com.fynnjason.mvvmarchitecture.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    val mainAdapter by lazy {
        MainAdapter()
    }

    fun getMainData() {
        mainAdapter.setList(
            listOf(
                "显示网络请求结果到TextView上",
                "显示网络请求结果到RecyclerView上",
                "显示网络请求结果到Banner上",
                "常规APP首页",
                "自带Toolbar的Activity",
                "拍照或相册选择照片，6.0权限",
                "Room数据库基础使用"
            )
        )
    }
}