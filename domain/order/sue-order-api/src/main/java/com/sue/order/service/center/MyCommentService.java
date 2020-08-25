package com.sue.order.service.center;


import com.sue.order.pojo.OrderItems;
import com.sue.order.pojo.dto.center.OrderItemsCommentDTO;
import com.sue.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/4 10:10
 */

@FeignClient("sue-order-service")
@RequestMapping("order-comments-api")
public interface MyCommentService {
    /**
     * 根据用户id查询关联的商品
     * @param orderId
     * @return
     */
    @GetMapping("/orderItems")
    List<OrderItems> queryPendingComment(
            @RequestParam("orderId") String orderId
    );

    /***
     * 保存用户的实现
     * @param orderId
     * @param userId
     * @param list
     */
    @PostMapping("/saveOrderComments")
    void saveComments(
            @RequestParam("orderId") String orderId,
            @RequestParam("userId") String userId,
            @RequestBody List<OrderItemsCommentDTO> list
    );
    //TODO 移到了itemCommentsService里
}
