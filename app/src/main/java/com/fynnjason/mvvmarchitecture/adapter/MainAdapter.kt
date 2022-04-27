package com.fynnjason.mvvmarchitecture.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.databinding.ItemMainBinding


class MainAdapter : BaseQuickAdapter<String, MainAdapter.MainViewHolder>(R.layout.item_main) {
    class MainViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemMainBinding? = DataBindingUtil.bind(view)
    }

    override fun convert(holder: MainViewHolder, item: String) {
        holder.binding?.title = item
        holder.binding?.executePendingBindings()
    }

}