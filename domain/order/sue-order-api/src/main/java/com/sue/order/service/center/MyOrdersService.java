package com.sue.order.service.center;


import com.sue.order.pojo.Orders;
import com.sue.order.pojo.vo.OrderStatusCountsVO;
import com.sue.pojo.IMOOCJSONResult;
import com.sue.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author sue
 * @date 2020/8/4 8:35
 */

@FeignClient("sue-order-service")
@RequestMapping("myOrder-api")
public interface MyOrdersService  {

    @GetMapping("/order/query")
    public PagedGridResult queryMyOrders(
            @RequestParam("userId") String userId,
            @RequestParam("orderStatus") Integer orderStatus,
            @RequestParam(value = "page",required = false)	Integer page,
            @RequestParam(value = "pageSize",required = false)	Integer pageSize
    );
    /**
     * 订单状态-》商家发货
     */
    @PostMapping("/order/delivered")
    void updateDeliverOrderStatus(
            @RequestParam("orderId")String orderId
    );

    /**
     * 查询我的订单
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/order/details")
    Orders queryMyOrder(
            @RequestParam("userId")	String userId,
            @RequestParam("orderId")String orderId
    );

    /***
     * 更新订单状态->确认收获
     * @param orderId
     * @return
     */
    @PostMapping("/order/received")
    boolean updateReceiveOrderStatus(
            @RequestParam("orderId")	String orderId
    );

    /**
     * 删除订单(逻辑删除)
     * @param userId
     * @param orderId
     * @return
     */
    @DeleteMapping("/order")
    boolean deleteOrder(
            @RequestParam("userId")	 String userId,
            @RequestParam("orderId") String orderId
    );

    /**
     * 查询用户订单数
     * @param userId
     */
    @GetMapping("/order/counts")
    OrderStatusCountsVO getOrderStatusCounts(
            @RequestParam("userId")	String userId
    );

    /***
     * 获得分页的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/order/trend")
    PagedGridResult getOrdersTrend(
            @RequestParam("userId")	String userId,
            @RequestParam(value = "page",required = false)	Integer page,
            @RequestParam(value = "pageSize",required = false)	Integer pageSize
    );

//    @GetMapping("/checkUserOrder")
//    public IMOOCJSONResult checkUserOrder(
//            @RequestParam("userId") String userId,
//            @RequestParam("orderId")String orderId
//    );
}
