package com.sue.user.controller.usercenter;


import com.sue.pojo.IMOOCJSONResult;
import com.sue.user.pojo.Users;
import com.sue.user.service.center.UserCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sue
 * @date 2020/8/3 13:21
 */

@RestController
@RequestMapping("/center")
@Api(value = "用户中心",tags = {"用户中心展示的相关接口"})
public class CenterController {
    @Autowired
    private UserCenterService userCenterService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("/userInfo")
    public IMOOCJSONResult userInfo(
            @ApiParam(name = "userId",value = "用户Id",required = true)
            @RequestParam String userId
    ){
        Users users = userCenterService.queryUserInfo(userId);
        return IMOOCJSONResult.ok(userId);
    }

}
