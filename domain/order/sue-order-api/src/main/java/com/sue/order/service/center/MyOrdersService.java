package com.sue.order.service.center;


import com.sue.order.pojo.Orders;
import com.sue.order.pojo.vo.OrderStatusCountsVO;
import com.sue.pojo.PagedGridResult;

/**
 * @author sue
 * @date 2020/8/4 8:35
 */

public interface MyOrdersService {

    /**
     * 查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus,
                                         Integer page, Integer pageSize);


    /**
     * 查询我的订单
     * @param userId
     * @param orderId
     * @return
     */
    public Orders queryMyOrder(String userId, String orderId);


    /**
     * 更新订单状态
     * @param orderId
     * @return
     */
    public boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId
     * @param orderId
     * @return
     */
    public boolean deleteOrder(String userId,String orderId);


    /**
     * 查询用户订单数
     * @param userId
     * @return
     */
    public OrderStatusCountsVO getOrderStatusCounts(String userId);


    /**
     * 获得分页订单动向
     * @param userId

     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult getOrdersTrend(String userId,Integer page,Integer pageSize);
}
