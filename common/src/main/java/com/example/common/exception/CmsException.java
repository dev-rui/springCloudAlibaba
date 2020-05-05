package com.example.common.exception;

import com.example.common.respose.ResultCodeEnum;
import lombok.Data;

/**
 * @Author heruihong
 * @Date 2020/5/5 23:30
 * @Version 1.0
 */
@Data
public class CmsException extends RuntimeException {


    private Integer code;


    public CmsException(Integer code, String message){
        super(message);
        this.code=code;
    }
    public CmsException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    @Override
    public String toString(){
        return "CMSException {"+"code="+code+",message="+this.getMessage()+"}";
    }



}
