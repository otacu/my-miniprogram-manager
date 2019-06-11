package com.egoist.miniprogram.controller;

import com.egoist.miniprogram.request.GetRouteRequest;
import com.egoist.miniprogram.request.SaveRouteRequest;
import com.egoist.miniprogram.service.RoutePointService;
import com.egoist.parent.common.constants.EgoistErrorMsgConstant;
import com.egoist.parent.common.constants.EgoistResultStatusConstants;
import com.egoist.parent.common.utils.string.EgoistStringUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 路线
 */
@Slf4j
@Controller
public class RouteController {

    @Autowired
    private RoutePointService routePointService;

    @PostMapping("miniprogram/route/save")
    @ResponseBody
    public EgoistResult saveRoute(@RequestBody SaveRouteRequest saveRouteRequest) {
        try {
            if (saveRouteRequest.getRoute() == null) {
                return new EgoistResult(EgoistResultStatusConstants.STATUS_400, EgoistErrorMsgConstant.MESSAGE_PARAM_ILLEGAL, null);
            }
            return routePointService.saveRoute(saveRouteRequest.getRoute());
        } catch (Exception e) {
            log.error("保存路线异常", e);
            return new EgoistResult(EgoistResultStatusConstants.STATUS_500, EgoistErrorMsgConstant.MESSAGE_SERVER_ERROR, null);
        }
    }

    @PostMapping("miniprogram/route/get")
    @ResponseBody
    public EgoistResult getRoute(@RequestBody GetRouteRequest getRouteRequest) {
        try {
            if (EgoistStringUtil.isBlank(getRouteRequest.getOpenId())) {
                return new EgoistResult(EgoistResultStatusConstants.STATUS_400, EgoistErrorMsgConstant.MESSAGE_PARAM_ILLEGAL, null);
            }
            return routePointService.queryByOpenId(getRouteRequest.getOpenId());
        } catch (Exception e) {
            log.error("获取路线异常", e);
            return new EgoistResult(EgoistResultStatusConstants.STATUS_500, EgoistErrorMsgConstant.MESSAGE_SERVER_ERROR, null);
        }
    }
}
