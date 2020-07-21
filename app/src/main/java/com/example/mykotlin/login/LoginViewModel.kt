package com.example.mykotlin.login

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.room.Room
import com.example.mykotlin.db.AppDatabase
import com.example.mykotlin.db.User
import com.example.mykotlin.kotlinText.ICallback

/**
 * ViewModel+liveData+lifecycleObserver
 */
class LoginViewModel(): ViewModel(),LifecycleObserver {

    val bean: MutableLiveData<UserBean> by lazy {
        MutableLiveData<UserBean>()
    }
    val text: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun login(name:String,pass:String){
    }
    lateinit var call:ICallback
    /**
     * 执行回调之前设值
     */
    fun setC(call:ICallback){
        this.call = call
        Log.e("------","---我设置了回调--")
    }
    lateinit var allList :List<User>;
    lateinit var someList :List<User>

    fun getDataForDB(context:AppCompatActivity){
        Log.e("------","---我要执行数据库操作--")
        Thread{
            var db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user").build()
            db.userDao().insertData(User(1,"威力","赵武"),
                User(2,"难道","的撒"),
                User(3,"的开","课啊"),
                User(4,"第四","案例"))
            allList= db.userDao().getAll()
            someList = db.userDao().loadDataById(1,2)
//            db.userDao().deleteData(User(1,"威力","赵武"))
            db.close()
            call.iKwon(allList,someList);
        }.start()
        Log.e("------","---我已经调用了数据库执行--")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
    }

}