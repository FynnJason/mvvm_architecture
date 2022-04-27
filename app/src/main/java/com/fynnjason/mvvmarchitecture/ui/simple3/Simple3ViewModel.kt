package com.fynnjason.mvvmarchitecture.ui.simple3

import com.fynnjason.mvvmarchitecture.adapter.ImageAdapter
import com.fynnjason.mvvmarchitecture.api.Api
import com.fynnjason.mvvmarchitecture.base.BaseViewModel
import com.fynnjason.mvvmarchitecture.bean.Simple3Bean
import com.fynnjason.mvvmarchitecture.net.NetCallback
import com.fynnjason.mvvmarchitecture.net.NetPresenter

class Simple3ViewModel : BaseViewModel() {
    val bannerAdapter by lazy {
        ImageAdapter(null)
    }

    fun getSimple3Data(){
        showLoading()
        NetPresenter(Simple3Bean())
            .get(Api.SIMPLE3, object : NetCallback<Simple3Bean> {
                override fun onSuccess(t: Simple3Bean, jsonString: String) {
                    hideLoading()
                    bannerAdapter.setDatas(t.data)
                }

                override fun onError(errorCode: Int, errorString: String) {
                    hideLoading()
                    bannerAdapter.setDatas(null)
                }

            })
    }
}