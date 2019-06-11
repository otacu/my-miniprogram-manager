package com.egoist.miniprogram.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String code;

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
