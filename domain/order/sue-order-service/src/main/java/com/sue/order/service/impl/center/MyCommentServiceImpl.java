package com.sue.order.service.impl.center;


import com.github.pagehelper.PageHelper;
import com.sue.enums.YesOrNO;
import com.sue.item.mapper.ItemsCommentsMapper;
import com.sue.item.pojo.ItemsComments;
import com.sue.item.pojo.vo.MyCommentVO;
import com.sue.item.service.ItemCommentsService;
import com.sue.order.mapper.OrderItemsMapper;
import com.sue.order.mapper.OrderStatusMapper;
import com.sue.order.mapper.OrdersMapper;
import com.sue.order.pojo.OrderItems;
import com.sue.order.pojo.OrderStatus;
import com.sue.order.pojo.Orders;
import com.sue.order.pojo.dto.center.OrderItemsCommentDTO;
import com.sue.order.service.center.MyCommentService;
import com.sue.pojo.PagedGridResult;
import com.sue.service.PagedGridResultUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sue
 * @date 2020/8/4 10:11
 */
@RestController
public class MyCommentServiceImpl implements MyCommentService {

    @Resource
    private OrderItemsMapper orderItemsMapper;

//    @Resource
//    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemCommentsService itemCommentsService;
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private Sid sid;

    /**
     * 根据订单id查询关联商品
     *
     * @param orderId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    /**
     * 保存用户的评价
     *
     * @param orderId
     * @param userId
     * @param commentDTOS
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentDTO> commentDTOS) {

        //1.保存评价 items_comments

        for (OrderItemsCommentDTO oic : commentDTOS) {
            oic.setCommentId(sid.nextShort());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentDTOS);
        itemCommentsService.saveComments(map);


        //2.修改订单表已评价
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setIsComment(YesOrNO.YES.type);
        ordersMapper.updateByPrimaryKeySelective(orders);


        //3.修改订单状态表的留言时间
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);


    }


//    /**
//     * 我的评价查询分页
//     *
//     * @param userId
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Override
//    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", userId);
//        PageHelper.startPage(page, pageSize);
//        List<MyCommentVO> myCommentVOS = itemsCommentsMapper.queryMyComments(map);
//
//        return PagedGridResultUtils.setterPagedGrid(myCommentVOS, page);
//    }
}
