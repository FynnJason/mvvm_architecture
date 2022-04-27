package com.fynnjason.mvvmarchitecture.ui.simple4

import androidx.lifecycle.MutableLiveData
import com.fynnjason.mvvmarchitecture.base.BaseViewModel

class Simple4ContainerViewModel : BaseViewModel() {
    val title = MutableLiveData<String>()
}