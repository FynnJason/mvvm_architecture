package com.fynnjason.mvvmarchitecture.ui.simple2

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.fynnjason.mvvmarchitecture.adapter.Simple2Adapter
import com.fynnjason.mvvmarchitecture.api.Api
import com.fynnjason.mvvmarchitecture.base.BaseViewModel
import com.fynnjason.mvvmarchitecture.bean.Simple2Bean
import com.fynnjason.mvvmarchitecture.net.NetCallback
import com.fynnjason.mvvmarchitecture.net.NetPresenter

class Simple2ViewModel : BaseViewModel() {
    val simple2Adapter by lazy {
        Simple2Adapter()
    }

    val refreshFinish = MutableLiveData<Boolean>()

    var page = 0

    fun getSimple2Data(loadMore: Boolean = false) {
        if (loadMore) {
            page++
        } else {
            page = 0
        }
        NetPresenter(Simple2Bean())
            .get("${Api.SIMPLE2}/$page/json", object : NetCallback<Simple2Bean> {
                override fun onSuccess(t: Simple2Bean, jsonString: String) {
                    refreshFinish.value = true
                    if (!loadMore) {
                        simple2Adapter.setList(t.data.datas)
                    } else {
                        if (t.data.datas.size == 0) {
                            simple2Adapter.loadMoreModule.loadMoreEnd()
                        } else {
                            simple2Adapter.addData(t.data.datas)
                            simple2Adapter.loadMoreModule.loadMoreComplete()
                        }
                    }
                }

                override fun onError(errorCode: Int, errorString: String) {
                    ToastUtils.showShort(errorString)
                    refreshFinish.value = true
                    if (loadMore) {
                        simple2Adapter.loadMoreModule.loadMoreComplete()
                    }
                }

            })

    }
}