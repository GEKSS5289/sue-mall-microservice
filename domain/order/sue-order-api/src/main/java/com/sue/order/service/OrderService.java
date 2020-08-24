package com.sue.order.service;


import com.sue.order.pojo.OrderStatus;
import com.sue.order.pojo.dto.SubmitOrderDTO;
import com.sue.order.pojo.vo.OrderVO;
import com.sue.pojo.ShopcartDTO;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/2 16:28
 */

public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param submitOrderDTO
     */
    public OrderVO createOrder(List<ShopcartDTO> list, SubmitOrderDTO submitOrderDTO);


    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);


    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    public OrderStatus queryOrderStatusInfo(String orderId);


    /**
     * 关闭超时未支付订单
     */
    public void closeOrder();

}
