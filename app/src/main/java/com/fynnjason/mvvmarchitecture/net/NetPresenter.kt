package com.fynnjason.mvvmarchitecture.net

import com.alibaba.fastjson.JSON
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response

class NetPresenter<T : BaseBean>(t: T) {

    private lateinit var mCallback: NetCallback<T>

    /**
     * GET请求
     */
    fun get(url: String, callback: NetCallback<T>, params: HttpParams? = null) {
        mCallback = callback
        OkGo.get<String>(url)
            .tag(url)
            .params(params)
            .execute(stringCallback)
    }

    /**
     * 统一处理回调
     */
    private val stringCallback: StringCallback = object : StringCallback() {
        override fun onSuccess(response: Response<String>?) {
            try {
                val bean = JSON.parseObject(response?.body(), t::class.java)
                if (bean.errorCode == 0) {
                    mCallback.onSuccess(bean, response?.body()!!)
                } else {
                    mCallback.onError(bean.errorCode, bean.errorMsg)
                }
            } catch (e: Exception) {
                mCallback.onError(-1, "数据异常！")
            }
        }

        override fun onError(response: Response<String>?) {
            mCallback.onError(500, "网络异常，请检查您的网络！")
        }
    }
}