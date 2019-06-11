package com.egoist.miniprogram.request;

import com.egoist.miniprogram.pojo.RoutePoint;

import java.io.Serializable;
import java.util.List;

/**
 * 保存路线请求
 */
public class SaveRouteRequest implements Serializable {

    private List<RoutePoint> route;

    /**
     * @return route
     */
    public List<RoutePoint> getRoute() {
        return route;
    }

    /**
     * @param route route
     */
    public void setRoute(List<RoutePoint> route) {
        this.route = route;
    }
}
