package com.example.discoveryserver.controller;

import cn.hutool.core.date.DateUtil;
import com.example.common.respose.Response;
import com.example.common.respose.ResultCodeEnum;
import com.example.discoveryserver.model.SysUser;
import com.example.discoveryserver.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rui
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @author: rui
     * @DateTime: 2020/8/24 17:34
     * @Description: 保存
     */
    @RequestMapping("/save")
    public  Response save(@RequestBody SysUser user){
        user.setCreateTime(DateUtil.date());
        userService.save(user);
        return Response.setResult(ResultCodeEnum.SUCCESS);
    }

    /**
     * @Author: rui
     * @DateTime: 2020/8/24 17:34
     * @Description: 更新
     */
    @RequestMapping("/update")
    public  Response Update(@RequestBody SysUser user){
        user.setUpdateTime(DateUtil.date());
        userService.updateById(user);
        return Response.setResult(ResultCodeEnum.SUCCESS);
    }


    /**
     * @Author: rui
     * @DateTime: 2020/8/24 17:34
     * @Description: 查询
     */
    @RequestMapping("/getOne")
    @ResponseBody
    public Response getOne(@RequestBody SysUser user){
    SysUser result=userService.getById(user.getId());
    return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }
    
    /**
     * @Author: rui
     * @DateTime: 2020/8/24 17:34
     * @Description: 查询
     */
    @RequestMapping("/getList")
    @ResponseBody
    public Response getList(){
        List<SysUser> result=userService.getlist();
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }
}

