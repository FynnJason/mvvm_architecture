package com.fynnjason.mvvmarchitecture.ui.simple4

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple4Binding

/**
 * 示例：
 * 常规App首页，底部+切换页
 */
class Simple4Activity : BaseActivity<ActivitySimple4Binding, Simple4ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple4Activity::class.java))
        }
    }

    private var mCurrentFragment: Fragment? = null
    private val mHomeFragment = Simple4ContainerFragment.newInstance("首页","")
    private val mTravelFragment = Simple4ContainerFragment.newInstance("出行","")
    private val mLiveFragment = Simple4ContainerFragment.newInstance("生活","")
    private val mMineFragment = Simple4ContainerFragment.newInstance("我的","")

    override fun layoutResID(): Int = R.layout.activity_simple4

    override fun initView() {
        changeFragment(mHomeFragment)
    }

    override fun initListener() {
        binding.layoutHome.setOnClickListener {
            viewModel.position.value = 0
            changeFragment(mHomeFragment)
        }
        binding.layoutTravel.setOnClickListener {
            viewModel.position.value = 1
            changeFragment(mTravelFragment)
        }
        binding.layoutLive.setOnClickListener {
            viewModel.position.value = 2
            changeFragment(mLiveFragment)
        }
        binding.layoutMine.setOnClickListener {
            viewModel.position.value = 3
            changeFragment(mMineFragment)
        }
    }

    override fun loadData() {

    }

    override fun eventBus(code: Int, msg: Any) {

    }

    /**
     * 切换Fragment
     */
    private fun changeFragment(currentFragment: Fragment) {
        // APP启动时，加载首页
        if (mCurrentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.layout_container, currentFragment)
                .commit()
            mCurrentFragment = currentFragment
            return
        }
        if (currentFragment != mCurrentFragment) {
            if (currentFragment.isAdded) {
                supportFragmentManager.beginTransaction()
                    .show(currentFragment)
                    .hide(mCurrentFragment!!)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.layout_container, currentFragment)
                    .hide(mCurrentFragment!!)
                    .commit()
            }
            mCurrentFragment = currentFragment
        }
    }
}