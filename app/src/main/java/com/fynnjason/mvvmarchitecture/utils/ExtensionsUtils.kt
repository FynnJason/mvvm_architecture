package com.fynnjason.mvvmarchitecture.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.fynnjason.mvvmarchitecture.utils.ViewClickDelay.SPACE_TIME
import com.fynnjason.mvvmarchitecture.utils.ViewClickDelay.lastClickTime

object ViewClickDelay {
    var lastClickTime: Long = 0
    const val SPACE_TIME: Long = 1000
}

/**
 * 防止短时间重复事件
 */
fun Any.oneClick(clickAction: () -> Unit) {
    val currentTime = System.currentTimeMillis()
    if (currentTime - lastClickTime > SPACE_TIME) {
        lastClickTime = System.currentTimeMillis()
        clickAction()
    }
}

fun EditText.searchListener(searchResult: () -> Unit) {
    this.setOnEditorActionListener { _, i, _ ->
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            searchResult()
            true
        } else {
            false
        }
    }
}

/**
 * 防止View短时间(1秒)内重复点击
 */
fun View.singleClick(action: () -> Unit) {
    this.setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime > SPACE_TIME) {
            lastClickTime = System.currentTimeMillis()
            action()
        }
    }
}

/**
 * 监听EditText输入后
 */
fun EditText.afterInput(input: (content: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            input(p0.toString())
        }
    })
}
 