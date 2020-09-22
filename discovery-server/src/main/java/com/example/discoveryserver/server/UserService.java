package com.example.discoveryserver.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.discoveryserver.model.SysUser;

import java.util.List;

public interface UserService extends IService<SysUser> {
    List<SysUser> getlist();

    String insert(String name);
}
