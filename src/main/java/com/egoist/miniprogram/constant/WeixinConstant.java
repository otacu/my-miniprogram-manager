package com.egoist.miniprogram.constant;

public final class WeixinConstant {
    private WeixinConstant() {
    }

    public static String AUTH_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
}
