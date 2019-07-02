package org.kirin.consumer.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.kirin.consumer.mybatis.pojo.VUser;
import org.kirin.consumer.mybatis.pojo.VUserExample;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.facade.response.UserInfoResp;
import org.kirin.consumer.mybatis.mapper.VUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hinsteny
 * @version $ID: UserService 2019-03-20 16:43 All rights reserved.$
 */
@Service
public class UserService {

    @Autowired
    private VUserMapper vUserMapper;

    public Long createUser(UserInfoReq user) {
        VUser vUser = new VUser();
        vUser.setUsername(user.getName());
        vUser.setPassword(user.getPassword());
        vUser.setSex(user.getSex());
        vUser.setEmail(user.getEmail());
        vUserMapper.insertSelective(vUser);
        return vUser.getId();
    }

    public List<UserInfoResp> searchUsersByName(String key) {
        VUserExample example = new VUserExample();
        example.createCriteria().andUsernameLike(key + "%");
        List<VUser> vUsers = vUserMapper.selectByExample(example);
        return CollectionUtils.isEmpty(vUsers) ? new ArrayList<>() : (vUsers.stream().map(vUser -> {
            UserInfoResp target = new UserInfoResp();
            BeanUtils.copyProperties(vUser, target);
            return target;
        }).collect(Collectors.toList()));
    }

}
