package com.springcloud.dao;

import com.springcloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //新建订单
    void create(Order order);

    //更新状态
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
