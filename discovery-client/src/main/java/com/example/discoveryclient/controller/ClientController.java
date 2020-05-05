package com.example.discoveryclient.controller;



import com.example.common.respose.Response;
import com.example.common.respose.ResultCodeEnum;
import com.example.discoveryapi.server.HelloService;
import com.example.discoveryclient.retryer.TechlogRetryer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = "客户端控制类")
public class ClientController {

    @Reference(version = "1.0.0")
    HelloService helloService;

    @ApiOperation(value="测试", notes="测试")
    @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String")
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Response test(@RequestBody String  name) {
        String result = helloService.hello(name);
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }

    @ApiOperation(value="检测重复请求", notes="测试")
    @ApiImplicitParam(name = "test", value = "test", required = true, dataType = "String")
    @GetMapping("/call")
    @TechlogRetryer(retryThrowable = Exception.class,waitMsec = 3,maxAttempt = 3)
    public Response call(String test) {
        String  result = helloService.hello(test);
        return Response.setResult(ResultCodeEnum.SUCCESS,result);
    }
}
