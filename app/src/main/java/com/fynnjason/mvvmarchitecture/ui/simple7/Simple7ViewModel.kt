package com.fynnjason.mvvmarchitecture.ui.simple7

import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.blankj.utilcode.util.Utils
import com.fynnjason.mvvmarchitecture.base.BaseViewModel
import com.fynnjason.mvvmarchitecture.room.Simple7EntityDatabase

class Simple7ViewModel : BaseViewModel() {
    val content = MutableLiveData<String>()

    val simple7EntityDao by lazy {
        Room.databaseBuilder(
            Utils.getApp(),
            Simple7EntityDatabase::class.java,
            "simple7entity_database"
        )
            .allowMainThreadQueries()
            .build()
            .simple7EntityDao
    }

    fun showContent() {
        val entities = simple7EntityDao.allEntities
        var string = ""
        for (entity in entities) {
            string += "${entity.id}:${entity.english},${entity.chinese}\n"
        }
        content.value = string
    }
}