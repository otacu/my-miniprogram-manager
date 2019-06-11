package com.egoist.miniprogram.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 小程序配置
 */
@ConfigurationProperties(prefix = "miniprogram")
public class MiniprogramConfig {

    private String appId;
    private String appSecret;

    /**
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @param appSecret appSecret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * @return appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @return appSecret
     */
    public String getAppSecret() {
        return appSecret;
    }
}
