package com.example.navigationlibrary.nav

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.os.*
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.baidu.navisdk.adapter.*
import com.baidu.navisdk.adapter.IBNTTSManager.IOnTTSPlayStateChangedListener
import com.baidu.navisdk.adapter.IBNaviListener.MapStateMode
import com.baidu.navisdk.adapter.struct.*
import com.baidu.navisdk.ui.routeguide.model.RGLineItem
import com.example.navigationlibrary.ControlBoardWindow
import com.example.navigationlibrary.GuidePop

/**
 * 诱导界面
 */
class BdnavActivity : Activity() {
    private var mRouteGuideManager: IBNRouteGuideManager? = null
    private val mMode = IBNaviListener.DayNightMode.DAY
    var view: View? = null
    var guidePop: GuidePop? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fullScreen = supportFullScreen()
        val params = Bundle()
        params.putBoolean(BNaviCommonParams.ProGuideKey.IS_SUPPORT_FULL_SCREEN, fullScreen)
        mRouteGuideManager = BaiduNaviManagerFactory.getRouteGuideManager()
        val config = BNGuideConfig.Builder()
            .params(params)
            .build()
        view = mRouteGuideManager?.onCreate(this, config)
        if (view != null) {
            setContentView(view)
        }
        guidePop = GuidePop(this)
        initTTSListener()
        routeGuideEvent()
    }

    // 导航过程事件监听
    private fun routeGuideEvent() {
        BaiduNaviManagerFactory.getRouteGuideManager().setNaviListener(object : IBNaviListener {
            override fun onRoadNameUpdate(name: String) { // 弹窗展示
                ControlBoardWindow.getInstance().showControl("当前路名更新——$name")
                guidePop?.setdata(name)
            }

            override fun onRemainInfoUpdate(
                remainDistance: Int,
                remainTime: Int
            ) { // 弹窗展示
                ControlBoardWindow.getInstance()
                    .showControl("距离目的地的剩余——" + remainDistance + "m " + remainTime + "s")
                guidePop?.setdata(remainDistance, remainTime)
            }

            override fun onViaListRemainInfoUpdate(msg: Message) {}
            override fun onGuideInfoUpdate(naviInfo: BNaviInfo) { // 弹窗展示
                ControlBoardWindow.getInstance().showControl("诱导信息——$naviInfo")
                guidePop?.setdata(naviInfo)
            }

            override fun onHighWayInfoUpdate(
                action: IBNaviListener.Action,
                info: BNHighwayInfo
            ) {
                ControlBoardWindow.getInstance()
                    .showControl("高速信息——$action $info")
            }

            override fun onFastExitWayInfoUpdate(
                action: IBNaviListener.Action,
                name: String,
                dist: Int,
                id: String
            ) {
                ControlBoardWindow.getInstance()
                    .showControl("快速路出口信息——" + action + " " + name + " 出口还有" + dist + "米")
            }

            override fun onEnlargeMapUpdate(
                action: IBNaviListener.Action,
                enlargeMap: View,
                remainDistance: String,
                progress: Int,
                roadName: String,
                turnIcon: Bitmap
            ) {
                ControlBoardWindow.getInstance().showControl("放大图回调信息——不想写了自己看吧_gb")
            }

            override fun onDayNightChanged(style: IBNaviListener.DayNightMode) {
                ControlBoardWindow.getInstance().showControl("日夜更替信息回调$style")
            }

            override fun onRoadConditionInfoUpdate(
                progress: Double,
                items: List<BNRoadCondition>
            ) {
                ControlBoardWindow.getInstance().showControl("路况信息更新 进度：$progress 路况：。。。")
            }

            override fun onMainSideBridgeUpdate(type: Int) {
                ControlBoardWindow.getInstance().showControl("主辅路、高架桥信息更新:$type 意义不明？？—gb")
            }

            override fun onLaneInfoUpdate(action: IBNaviListener.Action, laneItems: List<RGLineItem>?) {
                if (laneItems != null && laneItems.size > 0) {
                    ControlBoardWindow.getInstance().showControl("车道线信息更新:" + action + laneItems[0].toString() + " ...")
                }
            }

            override fun onSpeedUpdate(speed: String, isOverSpeed: Boolean) {
                ControlBoardWindow.getInstance()
                    .showControl("当前车速：$speed 是否超速：$isOverSpeed")
            }

            override fun onArriveDestination() {
                val info =
                    BaiduNaviManagerFactory.getRouteGuideManager().naviResultInfo
                Toast.makeText(
                    this@BdnavActivity, "导航结算数据: $info",
                    Toast.LENGTH_SHORT
                ).show()
                ControlBoardWindow.getInstance().showControl("抵达目的地：$info")
            }

            override fun onArrivedWayPoint(index: Int) {
                ControlBoardWindow.getInstance().showControl("到达途径点——$index")
            }

            override fun onLocationChange(naviLocation: BNaviLocation) {
                ControlBoardWindow.getInstance().showControl("GPS位置有更新时的回调:")
            }

            override fun onMapStateChange(mapStateMode: MapStateMode) {
                ControlBoardWindow.getInstance()
                    .showControlOnlyone(
                        "底图操作态和导航态的回调: 别老摸就不会老回调了_为什么不自动变回导航态？_gb",
                        "onMapStateChange"
                    )
            }

            override fun onStartYawing(s: String) {}
            override fun onYawingSuccess() {
                ControlBoardWindow.getInstance().showControl("偏航成功的回调")
            }

            override fun onYawingArriveViaPoint(i: Int) {}
            override fun onNotificationShow(msg: String) {
                Log.e(TAG, msg)
                ControlBoardWindow.getInstance().showControl("导航中通知型消息的回调$msg")
            }

            override fun onHeavyTraffic() {
                Log.e(TAG, "onHeavyTraffic")
                ControlBoardWindow.getInstance().showControl("导航中前方一公里出现严重拥堵的回调")
            }

            override fun onNaviGuideEnd() {
                finish()
            }
        })
        BaiduNaviManagerFactory.getRouteGuideManager().setNaviViewListener(
            object : IBNaviViewListener {
                override fun onMainInfoPanCLick() {
                    ControlBoardWindow.getInstance().showControl("诱导面板的点击")
                    guidePop?.showAtLocation(
                        view,
                        Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0
                    )
                }

                override fun onNaviTurnClick() {
                    ControlBoardWindow.getInstance().showControl("界面左上角转向操作的点击")
                }

                override fun onFullViewButtonClick(show: Boolean) {
                    ControlBoardWindow.getInstance().showControl("全览按钮的点击")
                }

                override fun onFullViewWindowClick(show: Boolean) {
                    ControlBoardWindow.getInstance().showControl("全览小窗口的点击")
                }

                override fun onNaviBackClick() {
                    Log.e(TAG, "onNaviBackClick")
                    ControlBoardWindow.getInstance().showControl("导航页面左下角退出按钮点击")
                }

                override fun onBottomBarClick(action: IBNaviViewListener.Action) {
                    ControlBoardWindow.getInstance().showControl("底部中间部分点击")
                }

                override fun onNaviSettingClick() {
                    Log.e(TAG, "onNaviSettingClick")
                    ControlBoardWindow.getInstance().showControl("底部右边更多设置按钮点击")
                }

                override fun onRefreshBtnClick() {
                    ControlBoardWindow.getInstance().showControl("刷新按钮")
                }

                override fun onZoomLevelChange(level: Int) {
                    ControlBoardWindow.getInstance().showControl("地图缩放等级:$level")
                }

                override fun onMapClicked(x: Double, y: Double) {
                    ControlBoardWindow.getInstance().showControlOnlyone("地图点击的回调(国测局GCJ02坐标):x=" + x + " y=" + y, "onMapClicked")
                }

                override fun onMapMoved() {
                    Log.e(TAG, "onMapMoved")
                    ControlBoardWindow.getInstance().showControl("移动地图的回调")
                }

                override fun onFloatViewClicked() {
                    ControlBoardWindow.getInstance().showControl("后台诱导悬浮窗的点击")
                    try {
                        val intent = Intent()
                        intent.setPackage(packageName)
                        intent.setClass(this@BdnavActivity, Class.forName(BdnavActivity::class.java.name))
                        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                        startActivity(intent)
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun initTTSListener() {
        // 注册同步内置tts状态回调
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedListener(
            object : IOnTTSPlayStateChangedListener {
                override fun onPlayStart() {
                    Log.e("BNSDKDemo", "ttsCallback.onPlayStart")
                    ControlBoardWindow.getInstance().showControl("ttsCallback.onPlayStart")
                }

                override fun onPlayEnd(speechId: String) {
                    Log.e("BNSDKDemo", "ttsCallback.onPlayEnd")
                    ControlBoardWindow.getInstance().showControl("ttsCallback.onPlayEnd")
                }

                override fun onPlayError(code: Int, message: String) {
                    Log.e("BNSDKDemo", "ttsCallback.onPlayError")
                    ControlBoardWindow.getInstance().showControl("ttsCallback.onPlayError")
                }
            }
        )

        // 注册内置tts 异步状态消息
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedHandler(
            object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    Log.e("BNSDKDemo", "ttsHandler.msg.what=" + msg.what)
                    ControlBoardWindow.getInstance()
                        .showControl("ttsHandler.msg.what=" + msg.what)
                }
            }
        )
    }

    private fun unInitTTSListener() {
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedListener(null)
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedHandler(null)
    }

    override fun onStart() {
        super.onStart()
//        if (BNDemoUtils.getBoolean(this, "float_window")) {
//            mRouteGuideManager!!.onForeground()
//        }
        mRouteGuideManager!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mRouteGuideManager!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mRouteGuideManager!!.onPause()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onStop() {
        super.onStop()
        mRouteGuideManager!!.onStop()
//        if (BNDemoUtils.getBoolean(this, "float_window")) {
//            mRouteGuideManager!!.onBackground()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mRouteGuideManager!!.onDestroy(false)
        unInitTTSListener()
        mRouteGuideManager = null
    }

    override fun onBackPressed() {
        mRouteGuideManager!!.onBackPressed(false, true)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mRouteGuideManager!!.onConfigurationChanged(newConfig)
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {}
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (!mRouteGuideManager!!.onKeyDown(keyCode, event)) {
            super.onKeyDown(keyCode, event)
        } else true
    }

    private fun supportFullScreen(): Boolean {
        if (Build.VERSION.SDK_INT >= 21) {
            val window = window
            val color: Int
            color = if (Build.VERSION.SDK_INT >= 23) {
                Color.TRANSPARENT
            } else {
                0x2d000000
            }
            window.statusBarColor = color
            if (Build.VERSION.SDK_INT >= 23) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                var uiVisibility = window.decorView.systemUiVisibility
                if (mMode == IBNaviListener.DayNightMode.DAY) {
                    uiVisibility = uiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                window.decorView.systemUiVisibility = uiVisibility
            } else {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        mRouteGuideManager!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mRouteGuideManager!!.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private val TAG = BdnavActivity::class.java.name
    }
}