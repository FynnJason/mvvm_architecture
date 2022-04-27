package com.fynnjason.mvvmarchitecture.net

interface NetCallback<T> {
    /**
     * 请求成功，返回数据对象和JSON字符串
     */
    fun onSuccess(t: T, jsonString: String)

    /**
     * 请求失败，返回失败code和error字符串
     */
    fun onError(errorCode: Int, errorString: String)
}