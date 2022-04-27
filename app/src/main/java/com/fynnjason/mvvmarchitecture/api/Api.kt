package com.fynnjason.mvvmarchitecture.api

/**
 * 请求地址
 */
object Api {
    @JvmStatic
    private val BASE_URL = "https://www.wanandroid.com" // 服务器地址

    @JvmStatic
    val SIMPLE3 = "$BASE_URL/banner/json" // simple1，simple3

    @JvmStatic
    val SIMPLE2 = "$BASE_URL/article/list" // simple2
}