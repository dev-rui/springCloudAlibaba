package com.example.discoveryserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.discoveryserver.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
