package com.fynnjason.mvvmarchitecture.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import java.lang.reflect.ParameterizedType
import com.fynnjason.mvvmarchitecture.BR
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.event.EBEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseToolbarActivity<VDB : ViewDataBinding, VM : BaseViewModel> :
    AppCompatActivity() {

    lateinit var binding: VDB
    lateinit var viewModel: VM

    abstract fun layoutResID(): Int
    abstract fun initView()
    abstract fun initListener()
    abstract fun loadData()
    abstract fun onBack()
    abstract fun title(): String
    abstract fun eventBus(code: Int, msg: Any)

    // 加载框
    private val mProgressDialog by lazy {
        ProgressDialog(this)
    }

    /**
     * 显示加载
     */
    private fun showLoading() {
        mProgressDialog.setMessage("加载中，请等待...")
        mProgressDialog.setCanceledOnTouchOutside(false)
        if (!mProgressDialog.isShowing) {
            mProgressDialog.show()
        }
    }

    /**
     * 隐藏加载
     */
    private fun hideLoading() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.cancel()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_toolbar_container)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID(), null, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding.setVariable(BR.vm, viewModel)
        viewModel.isShowLoading.observe(this) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        EventBus.getDefault().register(this)
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        val layoutContainer = findViewById<FrameLayout>(R.id.layout_container)
        layoutContainer.addView(binding.root)
        findViewById<ImageView>(R.id.iv_toolbar_back).setOnClickListener {
            onBack()
        }
        findViewById<TextView>(R.id.tv_toolbar_title).text = title()
        initView()
        initListener()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventMessage(event: EBEvent) {
        eventBus(event.code, event.msg)
    }

    /**
     * 通过泛型，反射class类
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }

    /**
     * 解决activity异常销毁fragment重叠问题
     */
    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        val fragmentTag = "android:support:fragments"
        outState.remove(fragmentTag)
    }

    //------------------------- 点击外部关闭软键盘 -------------------------//
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                if (v != null) {
                    hideKeyboard(v.windowToken)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isShouldHideInput(v: View?, ev: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = arrayOf(0, 0)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            if (ev.x > left && ev.x < right && ev.y > top && ev.y < bottom) {
                return false
            }
            return true
        }
        return false
    }

    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
    //------------------------- 点击外部关闭软键盘 -------------------------//
}