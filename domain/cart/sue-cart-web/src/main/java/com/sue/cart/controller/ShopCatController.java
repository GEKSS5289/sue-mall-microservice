package com.sue.cart.controller;

import com.sue.cart.service.CartService;
import com.sue.controller.BaseController;
import com.sue.pojo.IMOOCJSONResult;
import com.sue.pojo.ShopcartDTO;
import com.sue.utils.JsonUtils;
import com.sue.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sue
 * @date 2020/8/2 11:10
 */

@Api(value = "购物车接口controller", tags = {"购物车接口相关api"})
@RestController
@RequestMapping("/shopcart")
public class ShopCatController extends BaseController {


    @Autowired
    private RedisOperator redisOperator;
//    public static final Logger log = LoggerFactory.getLogger(com.sue.controller.mallcontroller.ShopCatController.class);

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartDTO shopcartDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        cartService.addItemToCart(userId,shopcartDTO);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "删除商品", notes = "删除商品", httpMethod = "POST")
    @PostMapping("del")
    public IMOOCJSONResult del(
            @RequestParam String userId,
            @RequestBody String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        cartService.removeItemFromCart(userId,itemSpecId);
        return IMOOCJSONResult.ok();
    }
}
