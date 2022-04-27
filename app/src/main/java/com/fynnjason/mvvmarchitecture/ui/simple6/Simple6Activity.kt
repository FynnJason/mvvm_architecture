package com.fynnjason.mvvmarchitecture.ui.simple6

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.fynnjason.mvvmarchitecture.R
import com.fynnjason.mvvmarchitecture.base.BaseToolbarActivity
import com.fynnjason.mvvmarchitecture.databinding.ActivitySimple6Binding
import com.fynnjason.mvvmarchitecture.glide.GlideApp
import com.fynnjason.mvvmarchitecture.utils.GlideEngine
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener

/**
 * 示例：
 * 拍照、单选和多选照片，自带了权限申请
 */
class Simple6Activity : BaseToolbarActivity<ActivitySimple6Binding, Simple6ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, Simple6Activity::class.java))
        }
    }

    override fun layoutResID(): Int = R.layout.activity_simple6

    override fun initView() {
        // 申请6.0权限
//        PermissionX.init(this)
//            .permissions(
//                Manifest.permission.CAMERA,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//            .onExplainRequestReason { scope, deniedList ->
//                // 被拒绝的能再次申请的权限
//
//            }
//            .request { allGranted, grantedList, deniedList ->
//                // 如果所有权限都同意了
//                if (allGranted) {
//
//                } else {
//
//                }
//            }

        binding.rvSimple6.layoutManager = GridLayoutManager(this, 3)
        binding.rvSimple6.adapter = viewModel.simple6Adapter

        viewModel.simple6Adapter.setList(mutableListOf(""))
    }

    override fun initListener() {
        binding.btnSingleCamera.setOnClickListener {
            PictureSelector.create(this)
                .openCamera(SelectMimeType.ofImage())
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        val r = result?.get(0)!!
                        GlideApp.with(this@Simple6Activity)
                            .load(r.path)
                            .into(binding.ivSimple6)
                    }

                    override fun onCancel() {

                    }

                })
        }

        binding.btnSinglePhoto.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .isDisplayCamera(false)
                .setSelectionMode(SelectModeConfig.SINGLE)
                .setImageEngine(GlideEngine.createGlideEngine())
                .forResult(object : OnResultCallbackListener<LocalMedia?> {
                    override fun onResult(result: ArrayList<LocalMedia?>) {
                        val r = result[0]!!
                        GlideApp.with(this@Simple6Activity)
                            .load(r.path)
                            .into(binding.ivSimple6)
                    }

                    override fun onCancel() {

                    }
                })
        }

        viewModel.simple6Adapter.addChildClickViewIds(R.id.iv_item_simple6_delete)
        viewModel.simple6Adapter.setOnItemClickListener { adapter, view, position ->
            // 如果点的是加号
            if (viewModel.simple6Adapter.data[position] == "") {
                PictureSelector.create(this)
                    .openGallery(SelectMimeType.ofImage())
                    .isDisplayCamera(false)
                    .setMaxSelectNum(viewModel.multiNum)
                    .setImageEngine(GlideEngine.createGlideEngine())
                    .forResult(object : OnResultCallbackListener<LocalMedia?> {
                        override fun onResult(result: ArrayList<LocalMedia?>) {
                            for (media in result) {
                                viewModel.simple6Adapter.addData(0, media!!.path)
                            }
                            viewModel.multiNum = viewModel.maxNum - viewModel.simple6Adapter.data.size
                            // 如果不能再选图片时，隐藏加号
                            if (viewModel.multiNum == 0) {
                                // 移除加号
                                viewModel.simple6Adapter.removeAt(9)
                            }
                        }

                        override fun onCancel() {

                        }
                    })
            }

        }
        viewModel.simple6Adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.iv_item_simple6_delete) {
                viewModel.simple6Adapter.removeAt(position)
                // 移除一个item时，可选图片数量就加一个
                viewModel.multiNum++
                if (viewModel.multiNum == 1) {
                    // 添加加号
                    viewModel.simple6Adapter.addData("")
                }
            }
        }
    }

    override fun loadData() {

    }

    override fun eventBus(code: Int, msg: Any) {

    }

    override fun onBack() {
        finish()
    }

    override fun title(): String = "标题"
}