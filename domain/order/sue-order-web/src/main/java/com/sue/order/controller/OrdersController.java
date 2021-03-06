package com.sue.order.controller;


import com.sue.controller.BaseController;
import com.sue.core.util.PayCenterDataUtils;
import com.sue.enums.OrderStatusEnum;
import com.sue.enums.PayMethod;
import com.sue.exception.mallexception.OrdersException;
import com.sue.order.pojo.OrderStatus;
import com.sue.order.pojo.dto.SubmitOrderDTO;
import com.sue.order.pojo.vo.OrderVO;
import com.sue.order.service.OrderService;
import com.sue.pojo.IMOOCJSONResult;
import com.sue.pojo.ShopcartDTO;
import com.sue.utils.CookieUtils;
import com.sue.utils.JsonUtils;
import com.sue.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sue
 * @date 2020/8/2 16:26
 */

@Api(value = "订单相关", tags = {"订单相关接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderDTO submitOrderDTO, HttpServletRequest request, HttpServletResponse response) {

        if (submitOrderDTO.getPayMethod() != PayMethod.WEIXIN.getType() &&
                submitOrderDTO.getPayMethod() != PayMethod.ALIPAY.getType()) {
            throw new OrdersException(20000);
        }


        String shopcartJson = redisOperator.get(FOODIE_SHOPCART+":"+submitOrderDTO.getUserId());
        List<ShopcartDTO> shopcartDTOS = new ArrayList<>();
        if(StringUtils.isBlank(shopcartJson)){
            throw new  OrdersException(20002);
        }
        shopcartDTOS = JsonUtils.jsonToList(shopcartJson,ShopcartDTO.class);
        //创建订单
        OrderVO order = orderService.createOrder(shopcartDTOS,submitOrderDTO);
        String orderId = order.getOrderId();


        //清理覆盖现有的redis中的购物车数据
        shopcartDTOS.removeAll(order.getToBeRemovedShopcatdList());
        redisOperator.set(FOODIE_SHOPCART+":"+submitOrderDTO.getUserId(), JsonUtils.objectToJson(shopcartDTOS));

        CookieUtils.setCookie(request,response,FOODIE_SHOPCART, JsonUtils.objectToJson(shopcartDTOS),true);

        if(!PayCenterDataUtils.sendPayCenter(order,restTemplate)){
            throw new OrdersException(20001);
        }


        return IMOOCJSONResult.ok(orderId);
    }

    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId){

        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);

        return HttpStatus.OK.value();
    }


    @PostMapping("/getPaidOrderInfo")
    public IMOOCJSONResult getPaidOrderInfo(String orderId){
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return IMOOCJSONResult.ok(orderStatus);
    }

}
