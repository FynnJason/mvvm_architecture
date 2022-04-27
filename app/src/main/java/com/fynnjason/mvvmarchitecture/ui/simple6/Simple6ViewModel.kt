package com.fynnjason.mvvmarchitecture.ui.simple6

import com.fynnjason.mvvmarchitecture.adapter.Simple6Adapter
import com.fynnjason.mvvmarchitecture.base.BaseViewModel

class Simple6ViewModel : BaseViewModel() {
    val simple6Adapter by lazy {
        Simple6Adapter()
    }

    var multiNum = 9 // 图片最大选择数量
    var maxNum = 10 // 容器最大数量
}