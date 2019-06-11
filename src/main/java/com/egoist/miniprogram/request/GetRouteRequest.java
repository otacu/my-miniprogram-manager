package com.egoist.miniprogram.request;

import java.io.Serializable;

public class GetRouteRequest implements Serializable {
    private String openId;

    /**
     * @return openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
