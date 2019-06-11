package com.egoist.miniprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.egoist.miniprogram.config.MiniprogramConfig;
import com.egoist.miniprogram.constant.WeixinConstant;
import com.egoist.miniprogram.request.LoginRequest;
import com.egoist.parent.common.constants.EgoistErrorMsgConstant;
import com.egoist.parent.common.constants.EgoistResultStatusConstants;
import com.egoist.parent.common.utils.http.EgoistOkHttp3Util;
import com.egoist.parent.common.utils.string.EgoistStringUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 与微信服务器通信的控制器
 */
@Slf4j
@Controller
public class WeixinController {

    @Autowired
    private MiniprogramConfig miniprogramConfig;

    @RequestMapping("miniprogram/hello")
    @ResponseBody
    public String hello() {
        return "hello world！";
    }

    @PostMapping("miniprogram/login")
    @ResponseBody
    public EgoistResult login(@RequestBody LoginRequest loginRequest) {
        try {
            String code = loginRequest.getCode();
            if (EgoistStringUtil.isBlank(code)) {
                return new EgoistResult(EgoistResultStatusConstants.STATUS_400, EgoistErrorMsgConstant.MESSAGE_PARAM_ILLEGAL, null);
            }
            code = EgoistStringUtil.trim(code);
            // 去微信服务器获取用户openId
            String url = String.format(WeixinConstant.AUTH_CODE2SESSION, miniprogramConfig.getAppId(), miniprogramConfig.getAppSecret(), code);
            String returnJson = EgoistOkHttp3Util.get(url);
            if (EgoistStringUtil.isBlank(returnJson)) {
                return new EgoistResult(EgoistResultStatusConstants.STATUS_400, EgoistErrorMsgConstant.MESSAGE_RESPONSE_EMPTY, null);
            }
            log.info("微信登录接口返回：" + returnJson);
            JSONObject response = JSONObject.parseObject(returnJson);
            Optional<String> openId = Optional.ofNullable(response.getString("openid"));
            Optional<String> sessionKey = Optional.ofNullable(response.getString("session_key"));
            Optional<String> unionId = Optional.ofNullable(response.getString("unionid"));
            if (EgoistStringUtil.isBlank(openId.orElse(EgoistStringUtil.EMPTY))) {
                return new EgoistResult(EgoistResultStatusConstants.STATUS_400, response.getString("errmsg"), null);
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("openId", openId.orElse(""));
            return EgoistResult.ok(resultMap);
        } catch (Exception e) {
            log.error("登录异常", e);
            return new EgoistResult(EgoistResultStatusConstants.STATUS_500, EgoistErrorMsgConstant.MESSAGE_SERVER_ERROR, null);
        }
    }


}
