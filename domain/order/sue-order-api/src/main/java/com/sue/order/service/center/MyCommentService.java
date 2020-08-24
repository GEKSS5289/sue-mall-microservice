package com.sue.order.service.center;


import com.sue.order.pojo.OrderItems;
import com.sue.order.pojo.dto.center.OrderItemsCommentDTO;
import com.sue.pojo.PagedGridResult;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/4 10:10
 */

public interface MyCommentService {



    /**
     * 根据订单id查询关联商品
     * @param orderId
     * @return
     */
    public List<OrderItems> queryPendingComment(String orderId);


    /**
     * 保存用户的评价
     * @param orderId
     * @param userId
     * @param commentDTOS
     */
    public void saveComments(String orderId, String userId, List<OrderItemsCommentDTO> commentDTOS);


    /**
     * 我的评价查询分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
