package com.sue.cart.service.impl;

import com.sue.cart.service.CartService;
import com.sue.pojo.IMOOCJSONResult;
import com.sue.pojo.ShopcartDTO;
import com.sue.utils.JsonUtils;
import com.sue.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.sue.controller.BaseController.FOODIE_SHOPCART;

/**
 * @author sue
 * @date 2020/8/25 9:55
 */

@RestController
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisOperator redisOperator;

    @Override
    public boolean addItemToCart(String userId, ShopcartDTO shopcartDTO) {

        if (StringUtils.isBlank(userId)) {
            return false;
        }

        //TODO 前端用户在登录情况下 添加商品到购物车 会同时在后端同步购物车到redis缓存
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART+":"+userId);
        List<ShopcartDTO> shopcartDTOS = null;

        if(StringUtils.isNotBlank(shopcartJson)){
            //redis中已经有购物车了
            shopcartDTOS = JsonUtils.jsonToList(shopcartJson,ShopcartDTO.class);
            //判断购物车中是否存在已有商品，如果有的话counts累加
            boolean isHaving = false;
            for(ShopcartDTO sc : shopcartDTOS){
                String tmpSpecId = sc.getSpecId();
                if(tmpSpecId.equals(shopcartDTO.getSpecId())){
                    sc.setBuyCounts(sc.getBuyCounts()+shopcartDTO.getBuyCounts());
                    isHaving = true;
                }
            }
            if(!isHaving){
                shopcartDTOS.add(shopcartDTO);
            }
        }else{
            //redis中没有购物车
            shopcartDTOS = new ArrayList<>();
            shopcartDTOS.add(shopcartDTO);
        }

        redisOperator.set(FOODIE_SHOPCART+":"+userId, JsonUtils.objectToJson(shopcartDTOS));

        return true;
    }

    @Override
    public boolean removeItemFromCart(String userId, @RequestParam("userId")String itemSpecId) {


        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return false;
        }


        //TODO 用户在页面删除购物车中的商品数据，如果此时用户以登录 则需要同步删除redis购物车中的数据
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART+":"+userId);
        if(StringUtils.isNotBlank(shopcartJson)){
            //redis 中已经有购物车了
            List<ShopcartDTO> shopcartDTOS = JsonUtils.jsonToList(shopcartJson,ShopcartDTO.class);
            //判断购物车中是否存在已有商品，如果有的话则删除
            for(ShopcartDTO sc:shopcartDTOS){
                String tempSpecId = sc.getSpecId();
                if(tempSpecId.equals(itemSpecId)){
                    shopcartDTOS.remove(sc);
                    break;
                }
            }
            //覆盖现有redis中的购物车
            redisOperator.set(FOODIE_SHOPCART+":"+userId, JsonUtils.objectToJson(shopcartDTOS));
        }

        return true;
    }

    @Override
    public boolean clearCart(String userId) {
        redisOperator.del(FOODIE_SHOPCART+":"+userId);
        return true;
    }
}
