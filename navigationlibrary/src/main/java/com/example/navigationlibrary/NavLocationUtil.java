package com.example.navigationlibrary;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.navisdk.adapter.BNRoutePlanNode;

/**
 * 37.81677, 112.535302  华润大厦
 *
 * 37.797443, 112.617131 火车南站
 */
public class NavLocationUtil {
    private double startLat;
    private double startLng;
    private String startName;

    private double endLat;
    private double endLng;
    private String endName;

    private NavLocationUtil() {
    }

    private NavLocationUtil(double startLat, double startLng, String startName, double endLat, double endLng, String endName) {
        this.startLat = startLat;
        this.startLng = startLng;
        this.startName = startName;
        this.endLat = endLat;
        this.endLng = endLng;
        this.endName = endName;
    }



    private PlanNode getstartPlanNode() {
        return PlanNode.withLocation(new LatLng(startLat, startLng));
    }

    private PlanNode getEndPlanNode() {
        return PlanNode.withLocation(new LatLng(endLat, endLng));
    }


    public BNRoutePlanNode getstartBNRoutePlanNode(){
        return new BNRoutePlanNode.Builder().longitude(startLng).latitude(startLat).name(startName).description(startName).build();
    }
    public BNRoutePlanNode getendBNRoutePlanNode(){
        return new BNRoutePlanNode.Builder().longitude(endLng).latitude(endLat).name(endName).description(endName).build();
    }
    /**
     * 骑行路径规划
     * ridingType  0 普通骑行
     * 1 电动车骑行
     */
    public BikingRoutePlanOption getBikingRoutePlanOption() {
        BikingRoutePlanOption option = new BikingRoutePlanOption();
        return option.from(getstartPlanNode()).to(getEndPlanNode());
    }

    //步行路径规划
    public WalkingRoutePlanOption getWalkingRoutePlanOption() {
        WalkingRoutePlanOption option = new WalkingRoutePlanOption();
        return option.from(getstartPlanNode()).to(getEndPlanNode());
    }

    /**
     * 驾车路径规划
     */
    public DrivingRoutePlanOption getDrivingRoutePlanOption() {
        DrivingRoutePlanOption option = new DrivingRoutePlanOption();
        return option.from(getstartPlanNode()).to(getEndPlanNode());
    }

    /**
     * 市内公交路径规划
     */
    public TransitRoutePlanOption getTransitRoutePlanOption() {
        TransitRoutePlanOption option = new TransitRoutePlanOption();
        return option.from(getstartPlanNode()).to(getEndPlanNode()).city("太原");
    }

    public static class Builder {
        public Builder() {
        }

        private double startLat;
        private double startLng;
        private String startName;

        private double endLat;
        private double endLng;
        private String endName;

        public Builder startLat(double startLat) {
            this.startLat = startLat;
            return this;
        }

        public Builder startLng(double startLng) {
            this.startLng = startLng;
            return this;
        }

        public Builder startName(String startName) {
            this.startName = startName;
            return this;
        }

        public Builder endLat(double endLat) {
            this.endLat = endLat;
            return this;
        }

        public Builder endLng(double endLng) {
            this.endLng = endLng;
            return this;
        }

        public Builder endName(String endName) {
            this.endName = endName;
            return this;
        }

        public NavLocationUtil build() {
            return new NavLocationUtil(startLat, startLng, startName, endLat, endLng, endName);
        }
    }

}
