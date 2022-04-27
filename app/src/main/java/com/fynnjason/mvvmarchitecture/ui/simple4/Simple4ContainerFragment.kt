package com.fynnjason.mvvmarchitecture.ui.simple4

import android.os.Bundle
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseFragment
import com.fynnjason.mvvmarchitecture.databinding.FragmentSimple4ContainerBinding

class Simple4ContainerFragment :
    BaseFragment<FragmentSimple4ContainerBinding, Simple4ContainerViewModel>() {
    private var param1: String? = null
    private var param2: String? = null

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Simple4ContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun layoutResID(): Int = R.layout.fragment_simple4_container

    override fun initView() {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel.title.value = param1
    }

    override fun initListener() {

    }

    override fun loadData() {

    }

    override fun eventBus(code: Int, msg: Any) {

    }
}