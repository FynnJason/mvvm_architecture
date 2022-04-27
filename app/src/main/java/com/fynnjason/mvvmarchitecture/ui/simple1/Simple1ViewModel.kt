package com.fynnjason.mvvmarchitecture.ui.simple1

import androidx.lifecycle.MutableLiveData
import com.fynnjason.mvvmarchitecture.api.Api
import com.fynnjason.mvvmarchitecture.base.BaseViewModel
import com.fynnjason.mvvmarchitecture.bean.Simple3Bean
import com.fynnjason.mvvmarchitecture.net.NetCallback
import com.fynnjason.mvvmarchitecture.net.NetPresenter


class Simple1ViewModel : BaseViewModel() {
    val content = MutableLiveData<String>()

    fun getSimple1Data() {
        showLoading()
        NetPresenter(Simple3Bean())
            .get(Api.SIMPLE3, object : NetCallback<Simple3Bean> {
                override fun onSuccess(t: Simple3Bean, jsonString: String) {
                    hideLoading()
                    content.value = jsonString
                }

                override fun onError(errorCode: Int, errorString: String) {
                    hideLoading()
                    content.value = errorString
                }

            })
    }
}