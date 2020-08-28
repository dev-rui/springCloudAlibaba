package com.example.discoveryserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * @author rui
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper=false)
public class SysUser extends Model<SysUser> {
    @TableId(value = "id",type= IdType.ID_WORKER)
    private Long id;

    private String name;

    private int sex;

    private Date createTime;

    private Date updateTime;
}
