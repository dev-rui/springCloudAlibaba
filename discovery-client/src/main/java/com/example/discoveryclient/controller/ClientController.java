package com.example.discoveryclient.controller;



import com.example.common.respose.Response;
import com.example.common.respose.ResultCodeEnum;
import com.example.discoveryapi.server.HelloService;
import com.example.discoveryclient.model.MyOrder;
import com.example.discoveryclient.retryer.TechlogRetryer;
import com.example.discoveryclient.server.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @author rui
 */
@Log4j2
@RestController
@Api(tags = "客户端控制类")
public class ClientController {

    @DubboReference(version = "1.0.0")
    HelloService helloService;
    @Resource
    private OrderService orderService;

    @ApiOperation(value="测试AT", notes="测试")
    @RequestMapping(value = "/testAt",method = RequestMethod.POST)
    public Response testAt(@RequestBody MyOrder myOrder) {
        String result=orderService.insertAT(myOrder);
        log.info(Response.setResult(ResultCodeEnum.SUCCESS,result));
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }


    @ApiOperation(value="测试TCC", notes="测试")
    @RequestMapping(value = "/testTcc",method = RequestMethod.POST)
    public Response testTcc(@RequestBody MyOrder myOrder) {
        String result=orderService.insertTCC(myOrder);
        log.info(Response.setResult(ResultCodeEnum.SUCCESS,result));
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }

    @ApiOperation(value="查询订单", notes="查询")
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Response select(@RequestBody MyOrder myOrder) {
        MyOrder result=orderService.getById(myOrder.getId());
        log.info(Response.setResult(ResultCodeEnum.SUCCESS,result));
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }

    @ApiOperation(value="检测重复请求", notes="测试")
    @ApiImplicitParam(name = "test", value = "test", required = true, dataType = "String")
    @GetMapping("/call")
    @TechlogRetryer(retryThrowable = Exception.class,waitMsec = 3,maxAttempt = 3)
    public Response call(String test) {
        String  result = helloService.helloAT(test);
        log.info(Response.setResult(ResultCodeEnum.SUCCESS,result));
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }


    @ApiOperation(value="发送消息", notes="测试")
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public Response message(String message) {
        String result=orderService.createMessage(message);
        log.info("rocketMq消息结果："+result);
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }
}
