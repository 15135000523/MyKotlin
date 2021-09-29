package com.example.mykotlin.login

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlin.R
import com.example.mykotlin.db.User
import com.example.mykotlin.kotlinText.ICallback
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ICallback {

    private lateinit var vm: LoginViewModel;
    private lateinit var drawe: TextView
    private lateinit var animator: ValueAnimator

    private var handler = Handler {
        when (it.what) {
            0x00 -> Log.e("--", "")
            0x00 -> Log.e("--", "")
            else -> {

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initVm();
        initClick()
        drawe = findViewById<TextView>(R.id.drawe)
        createAnimator()
    }

    fun initVm() {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(LoginViewModel::class.java)
        vm.setC(this)
        lifecycle.addObserver(vm)
        var vmObservable = Observer<UserBean> { user ->
            login_text.setText("名字：${user.name}，性别：${user.sex}")
            login_text.visibility = View.VISIBLE
        }
        vm.bean.observe(this, vmObservable)
    }

    @SuppressLint("WrongConstant")
    fun initClick() {
        login_login.setOnClickListener {
            animator.start()
//            when{
//                login_user.text.toString()==""-> Toast.makeText(this,"请输入账号",Toast.LENGTH_LONG);
//                login_pass.text.toString()==""->Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG);
//                else->{
//                    vm.login(login_user.text.toString(),login_pass.text.toString())
//                    vm.bean.value= UserBean("莎士比亚","","女");
//                    vm.getDataForDB(this)
//                }
//            }

        }
    }

    override fun iKwon(all: List<User>, some: List<User>) {
        for (a in all) {
            Log.e("------", "-all-${a.firstName}+${a.lastName}")
        }
        for (a in some) {
            Log.e("------", "--some-${a.firstName}+${a.lastName}")
        }
    }

    private fun createAnimator() {
        animator = ValueAnimator.ofInt(-300, 0)
        animator.duration = 1000
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
            var params = drawe.layoutParams as RelativeLayout.LayoutParams
            Log.e("params","-------"+animation.animatedValue as Int)
            params.bottomMargin = animation.animatedValue as Int
            drawe.layoutParams = params
            drawe.invalidate()
        })
    }
}
