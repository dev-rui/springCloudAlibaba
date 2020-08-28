package com.example.discoveryserver.server.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.discoveryserver.mapper.UserMapper;
import com.example.discoveryserver.model.SysUser;
import com.example.discoveryserver.server.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
/*import org.apache.dubbo.config.annotation.Service;*/

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Override
    public List<SysUser> getlist() {
        return baseMapper.selectList(Wrappers.<SysUser>lambdaQuery());
    }
}
