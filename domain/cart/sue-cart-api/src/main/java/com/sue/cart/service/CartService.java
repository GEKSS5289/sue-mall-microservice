package com.sue.cart.service;

import com.sue.pojo.ShopcartDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author sue
 * @date 2020/8/25 9:52
 */

@RequestMapping("cart-api")
public interface CartService {
    @PostMapping("addItem")
    public boolean addItemToCart(@RequestParam("userId") String userId, @RequestBody ShopcartDTO shopcartDTO);
    @PostMapping("removeItem")
    public boolean removeItemFromCart(@RequestParam("userId")String userId, @RequestParam("userId")String itemSpecId);
    @PostMapping("clearCart")
    public boolean clearCart(@RequestParam("userId")String userId);
}
