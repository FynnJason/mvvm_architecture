package com.fynnjason.mvvmarchitecture.app

import android.app.Application
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.net.NetUtils
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


class MVVMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化网络
        NetUtils.init(this)
        // 初始化工具
        Utils.init(this)
        // 监听全局异常
        CrashUtils.OnCrashListener {
            LogUtils.d(it.throwable)
        }
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, R.color.white)
            ClassicsHeader(context)
        }
    }

}