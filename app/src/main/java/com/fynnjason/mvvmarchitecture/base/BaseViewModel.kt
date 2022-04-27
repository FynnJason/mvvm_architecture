package com.fynnjason.mvvmarchitecture.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    // 是否显示加载
    val isShowLoading = MutableLiveData<Boolean>()

    /**
     * 显示加载
     */
    fun showLoading() {
        isShowLoading.value = true
    }

    /**
     * 隐藏加载
     */
    fun hideLoading() {
        isShowLoading.value = false
    }
}