package com.sue.order.service;


import com.sue.order.pojo.OrderStatus;
import com.sue.order.pojo.dto.SubmitOrderDTO;
import com.sue.order.pojo.vo.OrderVO;
import com.sue.pojo.ShopcartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/2 16:28
 */

@FeignClient("sue-order-service")
@RequestMapping("order-api")
public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param submitOrderDTO
     */
    @PostMapping("/placeOrder")
    public OrderVO createOrder(List<ShopcartDTO> list, SubmitOrderDTO submitOrderDTO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    @PostMapping("/updateStatus")
    void updateOrderStatus(
            @RequestParam("orderId") String orderId,
            @RequestParam("orderStatus") Integer orderStatus
    );

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    @GetMapping("/orderStatus")
    OrderStatus queryOrderStatusInfo(
            @RequestParam("orderId")String orderId
    );

    /**
     * 关闭超时未支付订单
     */
    @PostMapping("/closePendingOrders")
    void closeOrder();
}
