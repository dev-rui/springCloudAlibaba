package com.example.discoveryclient.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("my_order")
@EqualsAndHashCode(callSuper=false)
public class MyOrder extends Model<MyOrder> {
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;
}
