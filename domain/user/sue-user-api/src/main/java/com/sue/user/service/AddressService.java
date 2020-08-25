package com.sue.user.service;


import com.sue.user.pojo.UserAddress;
import com.sue.user.pojo.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/2 15:08
 */
@FeignClient("sue-user-service")
@RequestMapping("address-api")
public interface AddressService {
    /***
     * 根据用户id查询用户地址列表
     * @param userId
     * @return
     */
    @GetMapping("/addressList")
    public List<UserAddress> queryAll(@RequestParam("userId") String userId);

    /**
     * 用户新增地址
     * @param addressDTO
     */
    @PostMapping("/address")
    public void addNewUserAddress(@RequestBody AddressDTO addressDTO);


    /**
     * 用户修改地址
     * @param addressDTO
     */
    @PutMapping("/address")
    public void updateUserAddress(@RequestBody AddressDTO addressDTO);


    /**
     * 用户删除地址
     * @param userId
     * @param addressId
     */
    @DeleteMapping("/address")
    public void deleteUserAddress(@RequestParam("userId")	String userId,
                                  @RequestParam("addressId")	String addressId);


    /**
     * 用户设置默认地址
     * @param userId
     * @param addressId
     */
    @PostMapping("/setDefaultAddress")
    public void updateUserAddressToBeDefault(@RequestParam("userId")	String userId,
                                             @RequestParam("addressId")	String addressId);


    /**
     * 根据用户Id和地址Id，查询具体用户地址对象信息
     * @param userId
     * @param addressId
     * @return
     */
    @GetMapping("/queryAddress")
    public UserAddress queryUserAddress(@RequestParam("userId")	String userId,
                                        @RequestParam(value = "addressId",required = false)	String addressId);
}
