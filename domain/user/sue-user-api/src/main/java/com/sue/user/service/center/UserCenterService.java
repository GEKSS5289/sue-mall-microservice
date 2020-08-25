package com.sue.user.service.center;


import com.sue.user.pojo.Users;
import com.sue.user.pojo.dto.center.CenterUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author sue
 * @date 2020/8/3 13:25
 */
@FeignClient("sue-user-service")
@RequestMapping("center-user-api")
public interface UserCenterService {
    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/profile")
    public Users queryUserInfo(@RequestParam("userId") String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserDTO
     * @return
     */
    @PutMapping("/profile/{userId}")
    public Users updateUserInfo(@PathVariable("userId")String userId,
                                @RequestBody CenterUserDTO centerUserDTO);


    /**
     * 修改用户头像
     * @param userId
     * @param faceUrl
     * @return
     */
    @PostMapping("/updatePhoto")
    public Users updateUserFace(@RequestParam("userId")	String userId,
                                    @RequestParam("faceUrl") String faceUrl);


}
