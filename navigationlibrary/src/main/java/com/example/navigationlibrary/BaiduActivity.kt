package com.example.navigationlibrary


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.search.core.SearchResult
import com.baidu.mapapi.search.route.*
import com.baidu.navisdk.adapter.BNRoutePlanNode
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory
import com.baidu.navisdk.adapter.IBNRoutePlanManager
import com.example.navigationlibrary.R.*
import com.example.navigationlibrary.adapter.ModeSelectiAdapter
import com.example.navigationlibrary.bean.ModeSelectBean
import com.example.navigationlibrary.nav.BdnavActivity
import com.example.navigationlibrary.overlayutil.*


class BaiduActivity : Activity() {
    val APP_FOLDER_NAME: String = "YAN"

    var mBaiduMap: BaiduMap? = null
    var mSearch: RoutePlanSearch? = null
    lateinit var mapview: MapView
    lateinit var topbar: View
    lateinit var recyclerView: RecyclerView
    lateinit var adpater: ModeSelectiAdapter

    lateinit var rules: List<String>
    lateinit var util: NavLocationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_baidu)
        util = NavLocationUtil.Builder().startLat(37.81677).startLng(112.535302).startName("华润大厦")
            .endLat(37.797443).endLng(112.617131).endName("火车南站").build()
        initView()
    }


    fun initView() {
        mapview = findViewById(id.mapview)
        topbar = findViewById(id.top_bar)

        mBaiduMap = mapview.map
        mBaiduMap!!.setMyLocationEnabled(true)

        initAdapter()
        //路线规划检索监听器
        mSearch = RoutePlanSearch.newInstance()
        mSearch?.setOnGetRoutePlanResultListener(listener)

        mSearch!!.walkingSearch(util.walkingRoutePlanOption)

        findViewById<ImageView>(id.btn_select_reversal).setOnClickListener {}
        findViewById<ImageView>(id.btn_select_nav).setOnClickListener {
            bdlocatiuon()
        }
    }

    fun initAdapter() {
        recyclerView = topbar.findViewById(id.select_type)
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        var list = ArrayList<ModeSelectBean>()
        list.add(ModeSelectBean(mipmap.iv_walk, "步行"))
        list.add(ModeSelectBean(mipmap.iv_bike, "骑行"))
        list.add(ModeSelectBean(mipmap.iv_driver, "驾车"))
        list.add(ModeSelectBean(mipmap.iv_bus, "公交"))
        adpater = ModeSelectiAdapter(list, this)
        adpater.setListener {
            adpater.selectPosition = it
            adpater.notifyDataSetChanged()
            mBaiduMap?.clear()
            when (it) {
                0 -> {
                    mSearch!!.walkingSearch(util.walkingRoutePlanOption)
                }
                1 -> {
                    mSearch!!.bikingSearch(util.bikingRoutePlanOption)
                }
                2 -> {
                    mSearch!!.drivingSearch(util.drivingRoutePlanOption)
                }
                3 -> {
                    mSearch!!.transitSearch(util.transitRoutePlanOption)
                }
            }
        }
        recyclerView.adapter = adpater
    }

    /**
     * 开始算路
     * 112.535302,37.81677
     *
     * 112.617131,37.797443
     */
    private fun bdlocatiuon() {
        Log.e(APP_FOLDER_NAME, "点击了")
        val sNode: BNRoutePlanNode = BNRoutePlanNode.Builder()
            .latitude(37.81677)
            .longitude(112.535302)
            .name("华润大厦")
            .description("华润大厦")
            .build()
        val eNode: BNRoutePlanNode = BNRoutePlanNode.Builder()
            .latitude(37.797443)
            .longitude(112.617131)
            .name("火车南站")
            .description("火车南站")
            .build()
        val list: MutableList<BNRoutePlanNode> = ArrayList()
        list.add(sNode)
        list.add(eNode)
        BaiduNaviManagerFactory.getRoutePlanManager().routePlanToNavi(list,
            IBNRoutePlanManager.RoutePlanPreference.ROUTE_PLAN_PREFERENCE_DEFAULT,
            null,
            Handler {
                when (it.what) {
                    IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_START -> {
                        Log.e(APP_FOLDER_NAME, "算路开始")
                    }
                    IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_SUCCESS -> {
                        Log.e(APP_FOLDER_NAME, "算路成功")
                    }
                    IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_FAILED -> {
                        Log.e(APP_FOLDER_NAME, "算路失败")
                    }
                    IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_TO_NAVI -> {
                        Log.e(APP_FOLDER_NAME, "算路成功准备进入导航")
                        startActivity(Intent(this, BdnavActivity::class.java))
                    }
                }
                return@Handler true
            }
        )
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
        mBaiduMap = null
    }


    var listener = object : OnGetRoutePlanResultListener {
        override fun onGetIndoorRouteResult(p0: IndoorRouteResult?) {
            TODO("Not yet implemented")
        }

        override fun onGetTransitRouteResult(p0: TransitRouteResult) {
            routeResult(p0)
        }

        override fun onGetDrivingRouteResult(p0: DrivingRouteResult) {
            routeResult(p0)
        }

        override fun onGetWalkingRouteResult(p0: WalkingRouteResult) {
            routeResult(p0)
        }

        override fun onGetMassTransitRouteResult(p0: MassTransitRouteResult) {
            routeResult(p0)
        }

        override fun onGetBikingRouteResult(p0: BikingRouteResult) {
            routeResult(p0)
        }

    }


    private fun routeResult(p0: SearchResult) {
        var overlay: OverlayManager? = null
        when (p0) {
            is BikingRouteResult -> {
                overlay = BikingRouteOverlay(mBaiduMap)
                if (p0.routeLines != null && p0.routeLines.size > 0)
                    overlay.setData(p0.routeLines[0])
            }
            is MassTransitRouteResult -> {
                overlay = MassTransitRouteOverlay(mBaiduMap)
                if (p0.routeLines != null && p0.routeLines.size > 0)
                    overlay.setData(p0.routeLines[0])
            }
            is WalkingRouteResult -> {
                overlay = WalkingRouteOverlay(mBaiduMap)
                if (p0.routeLines != null && p0.routeLines.size > 0)
                    overlay.setData(p0.routeLines[0])
            }
            is DrivingRouteResult -> {
                overlay = DrivingRouteOverlay(mBaiduMap)
                if (p0.routeLines != null && p0.routeLines.size > 0)
                    overlay.setData(p0.routeLines[0])
            }
            is TransitRouteResult -> {
                overlay = TransitRouteOverlay(mBaiduMap)
                if (p0.routeLines != null && p0.routeLines.size > 0)
                    overlay.setData(p0.routeLines[0])
            }
        }
        overlay?.addToMap()
        overlay?.zoomToSpan()
    }

}