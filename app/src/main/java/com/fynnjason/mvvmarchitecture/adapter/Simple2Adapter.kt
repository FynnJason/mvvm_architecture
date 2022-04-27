package com.fynnjason.mvvmarchitecture.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.bean.Simple2Bean
import com.fynnjason.mvvmarchitecture.databinding.ItemSimple2Binding


class Simple2Adapter : BaseQuickAdapter<Simple2Bean.DataBean.DatasBean, Simple2Adapter.Simple2ViewHolder>(R.layout.item_simple2),LoadMoreModule {
    class Simple2ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemSimple2Binding? = DataBindingUtil.bind(view)
    }

    override fun convert(holder: Simple2ViewHolder, item: Simple2Bean.DataBean.DatasBean) {
        holder.binding?.bean = item
        holder.binding?.executePendingBindings()
    }

}