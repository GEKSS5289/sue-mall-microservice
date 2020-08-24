package com.sue.order.mapper;


import com.sue.my.MyMapper;
import com.sue.order.pojo.OrderStatus;
import com.sue.order.pojo.Orders;
import com.sue.order.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends MyMapper<Orders> {
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String,Object> map);
    int getMyOrderStatusCounts(@Param("paramsMap") Map<String,Object> map);
    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String,Object> map);
}