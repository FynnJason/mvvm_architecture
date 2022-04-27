package com.fynnjason.mvvmarchitecture.ui.simple4

import androidx.lifecycle.MutableLiveData
import com.fynnjason.mvvmarchitecture.base.BaseViewModel

class Simple4ViewModel : BaseViewModel() {
    val position = MutableLiveData<Int>()
}