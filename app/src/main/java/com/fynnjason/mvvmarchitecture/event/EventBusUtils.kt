package com.fynnjason.mvvmarchitecture.event

import org.greenrobot.eventbus.EventBus

object EventBusUtils {
    /**
     * 发送消息
     */
    fun post(code: Int, msg: Any, isSticky: Boolean = false) {
        if (isSticky) {
            EventBus.getDefault().postSticky(EBEvent(code, msg))
        } else {
            EventBus.getDefault().post(EBEvent(code, msg))
        }
    }
}