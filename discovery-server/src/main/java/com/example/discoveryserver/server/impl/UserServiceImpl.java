package com.example.discoveryserver.server.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.discoveryserver.mapper.UserMapper;
import com.example.discoveryserver.model.SysUser;
import com.example.discoveryserver.server.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author rui
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Override
    public List<SysUser> getlist() {
        return baseMapper.selectList(Wrappers.<SysUser>lambdaQuery());
    }

    @Transactional
    @Override
    public String insert(String name) {
      int id=baseMapper.insert(new SysUser(name,1,new Date(),null));
      if(id>0){
         return "success";
      }
      return "filne";
    }
}
