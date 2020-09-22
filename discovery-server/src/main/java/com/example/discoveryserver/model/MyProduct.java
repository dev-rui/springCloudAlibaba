package com.example.discoveryserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("my_product")
@EqualsAndHashCode(callSuper=false)
public class MyProduct extends Model<MyProduct> {
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    public MyProduct(String name, Date createTime, Date updateTime) {
        this.name=name;
        this.createTime=createTime;
        this.updateTime=updateTime;
    }
}
