package com.example.discoveryserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.discoveryserver.model.MyProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<MyProduct> {

}
