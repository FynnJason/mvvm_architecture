package com.fynnjason.mvvmarchitecture.base


import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType
import com.fynnjason.mvvmarchitecture.BR
import com.fynnjason.mvvmarchitecture.event.EBEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var mView: View? = null
    private var mIsLoad = false

    lateinit var binding: VDB
    lateinit var viewModel: VM

    abstract fun layoutResID(): Int
    abstract fun initView()
    abstract fun initListener()
    abstract fun loadData()
    abstract fun eventBus(code: Int, msg: Any)

    // 加载框
    private val mProgressDialog by lazy {
        ProgressDialog(context)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutResID(), container, false)
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
            mView = binding.root
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mIsLoad) {
            mIsLoad = true
            EventBus.getDefault().register(this)
            initView()
            initListener()
            loadData()
        }
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
     * 反射获取ViewModel类
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }
}