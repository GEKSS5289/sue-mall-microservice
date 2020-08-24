package com.sue.user.service.impl.center;


import com.sue.user.mapper.UsersMapper;
import com.sue.user.pojo.Users;
import com.sue.user.pojo.dto.center.CenterUserDTO;
import com.sue.user.service.center.UserCenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author sue
 * @date 2020/8/3 13:26
 */

@Service
public class UserCenterServiceImpl implements UserCenterService {


    @Resource
    private UsersMapper usersMapper;


    /**
     * 根据userId查询用户信息
     *
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {

        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;

    }


    /**
     * 修改用户信息
     *
     * @param userId
     * @param centerUserDTO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(String userId, CenterUserDTO centerUserDTO) {

        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserDTO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(updateUser);

        Users users = queryUserInfo(userId);

        return users;

    }


    /**
     * 修改用户头像
     *
     * @param userId
     * @param faceUrl
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserFace(String userId, String faceUrl) {


        Users updateUser = new Users();
        updateUser.setId(userId);
        updateUser.setFace(faceUrl);
        updateUser.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(updateUser);

        Users users = queryUserInfo(userId);

        return users;

    }
}
