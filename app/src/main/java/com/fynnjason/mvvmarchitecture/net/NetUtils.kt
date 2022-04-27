package com.fynnjason.mvvmarchitecture.net

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * 网络工具
 */
object NetUtils {
    private const val LOG_TAG = "NetUtils" // 日志标识
    private val LOG_PRINT_LEVEL = HttpLoggingInterceptor.Level.BODY // 日志内容等级
    private val LOG_COLOR_LEVEL = Level.INFO // 日志内容颜色等级
    private const val READ_TIME_OUT: Long = 30 // 读取超时时间(秒)
    private const val WRITE_TIME_OUT: Long = 30 // 写入超时时间(秒)
    private const val CONNECT_TIME_OUT: Long = 30 // 连接超时时间(秒)
    private const val RETRY_COUNT = 0 // 超时重试次数

    /**
     * 必须在Application中初始化
     */
    fun init(application: Application) {
        // 创建网络构造器
        val builder = OkHttpClient.Builder()
        // 创建日志拦截器
        val loggingInterceptor = HttpLoggingInterceptor(LOG_TAG)
        // 设置日志拦截器拦截的内容等级
        loggingInterceptor.setPrintLevel(LOG_PRINT_LEVEL)
        // 设置日志拦截器打印的内容颜色等级
        loggingInterceptor.setColorLevel(LOG_COLOR_LEVEL)
        // 添加日志拦截器
        builder.addInterceptor(loggingInterceptor)
        // 设置读取超时时间
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        // 设置写入超时时间
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        // 设置连接超时时间
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        // 获取网络客户端
        val client = builder.build()
        // 初始化OkGo
        OkGo.getInstance().init(application)
            .setRetryCount(RETRY_COUNT)
            .setOkHttpClient(client)
    }
}