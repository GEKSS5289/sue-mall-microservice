package com.sue.user.service;


import com.sue.user.pojo.Users;
import com.sue.user.pojo.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author sue
 * @date 2020/8/1 8:38
 */
@FeignClient("sue-user-service")
@RequestMapping("user-api")
public interface UserService {
    /**
     * 判断用户是否存在
     */
    @GetMapping("/user/exists")
    public boolean queryUsernameIsExist(@RequestParam("username") String userName);

    /**
     * 创建用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/user")
    public Users createUser(@RequestBody UserDTO userDTO);

    /**
     * 检索用户名和密码是否匹配
     */
    @GetMapping("/verify")
    public Users queryUserForLogin(@RequestParam("username") String username,
                                   @RequestParam("password") String password);

}
