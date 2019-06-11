package com.egoist.miniprogram.dao;

import com.egoist.miniprogram.pojo.RoutePoint;
import com.egoist.miniprogram.pojo.RoutePointExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutePointMapper {
    long countByExample(RoutePointExample example);

    int deleteByExample(RoutePointExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoutePoint record);

    int insertSelective(RoutePoint record);

    List<RoutePoint> selectByExample(RoutePointExample example);

    RoutePoint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoutePoint record, @Param("example") RoutePointExample example);

    int updateByExample(@Param("record") RoutePoint record, @Param("example") RoutePointExample example);

    int updateByPrimaryKeySelective(RoutePoint record);

    int updateByPrimaryKey(RoutePoint record);
}