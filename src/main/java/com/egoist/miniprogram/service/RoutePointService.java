package com.egoist.miniprogram.service;

import com.egoist.miniprogram.dao.RoutePointMapper;
import com.egoist.miniprogram.pojo.RoutePoint;
import com.egoist.miniprogram.pojo.RoutePointExample;
import com.egoist.parent.common.constants.EgoistErrorMsgConstant;
import com.egoist.parent.common.constants.EgoistResultStatusConstants;
import com.egoist.parent.common.utils.collection.EgoistCollectionUtil;
import com.egoist.parent.pojo.dto.EgoistResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RoutePointService {
    @Autowired
    private RoutePointMapper routePointMapper;

    /**
     * 根据openId查询
     *
     * @param openId openId
     * @return 结果
     */
    public EgoistResult queryByOpenId(String openId) {
        log.info(openId);
        RoutePointExample example = new RoutePointExample();
        RoutePointExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        example.setOrderByClause("`order` asc");
        List<RoutePoint> list = routePointMapper.selectByExample(example);
        if (EgoistCollectionUtil.isEmpty(list)) {
            return new EgoistResult(EgoistResultStatusConstants.STATUS_400, EgoistErrorMsgConstant.MESSAGE_QUERY_ERROR, null);
        }
        return EgoistResult.ok(list);
    }

    /**
     * 新增
     *
     * @param list 路线
     * @return 结果
     */
    @Transactional
    public EgoistResult saveRoute(List<RoutePoint> list) {
        RoutePointExample example = new RoutePointExample();
        RoutePointExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(list.get(0).getOpenId());
        routePointMapper.deleteByExample(example);
        for (RoutePoint routePoint : list) {
            routePointMapper.insert(routePoint);
        }
        return EgoistResult.ok();
    }

}
