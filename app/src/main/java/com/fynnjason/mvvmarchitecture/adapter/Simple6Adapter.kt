package com.fynnjason.mvvmarchitecture.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.databinding.ItemSimple6Binding
import com.fynnjason.mvvmarchitecture.glide.GlideApp


class Simple6Adapter :
    BaseQuickAdapter<String, Simple6Adapter.Simple6ViewHolder>(R.layout.item_simple6, null) {
    class Simple6ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemSimple6Binding? = DataBindingUtil.bind(view)
    }

    override fun convert(holder: Simple6ViewHolder, item: String) {
        if (item == "") {
            GlideApp.with(context)
                .load(R.drawable.ic_simple6_photo_add)
                .into(holder.binding?.ivItemSimple6Add!!)
            holder.binding.ivItemSimple6Delete.visibility = View.GONE
        } else {
            GlideApp.with(context)
                .load(item)
                .into(holder.binding?.ivItemSimple6Add!!)
            holder.binding.ivItemSimple6Delete.visibility = View.VISIBLE
        }
    }
}