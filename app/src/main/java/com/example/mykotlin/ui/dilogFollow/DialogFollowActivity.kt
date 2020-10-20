package com.example.mykotlin.ui.dilogFollow

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityDialogFollowBinding
import com.example.mykotlin.rajava.MlxObservable
import com.example.mykotlin.rajava.MlxObservableOnSubscribe
import com.example.mykotlin.rajava.MlxObserver
import com.example.mykotlin.utils.DialogUtils
import com.tencent.mmkv.MMKV

class DialogFollowActivity : BaseActivity<DialogFollowViewModel, ActivityDialogFollowBinding>() {

    lateinit var adapter: DialogFollowAdapter

    override fun initViewModel(): Class<DialogFollowViewModel> = DialogFollowViewModel::class.java
    override fun loadLayout(): Int = R.layout.activity_dialog_follow

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        mDataBinding.run {
            recycler.layoutManager = GridLayoutManager(this@DialogFollowActivity, 3)
            adapter = DialogFollowAdapter(this@DialogFollowActivity, {
                hiView.visibility = View.VISIBLE
                hiView.setBackgroundColor(getColor(R.color.YellowAL))
                it.setBackgroundColor(getColor(R.color.white))
                DialogUtils.getInstance().createDialog(this@DialogFollowActivity, it)
            }).also { recycler.adapter = it }
        }
    }

    override fun initObserver() {

        MlxObservable.create(object : MlxObservableOnSubscribe<Int> {
            override fun setObserver(emitter: MlxObserver<Int>) {
                Log.e("Observable","上游发送数据:10")
                emitter.onNext(10)
            }
        })
            .setObserver<Int>(object :MlxObserver<Int>{
                override fun onSubscribe() {
                    Log.e("Observable","onSubscribe:")
                }
                override fun onNext(item: Int) {
                    Log.e("Observable","下游接收数据:"+item)
                }
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }


}