package com.gy.wo.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户
 * @author gy
 * @date 2017-07-21
 */
@EqualsAndHashCode()
@Data
@Entity
@Table(name = "wo_user")
public class User{
    @Id
    @GenericGenerator(name = "generateUUID", strategy = "uuid")
    @GeneratedValue(generator = "generateUUID")
    private String id;
    /**
     * 用户账号
     */
    private String  account;
    /**
     * 用户密码
     */
    private String  password;
    /**
     * 用户code
     */
    private String userCode;
    /**
     * 员工ID
     */
    private Long    employeeId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    createTime;
    /**
     * 账号状态
     */
    private Integer state;
    /**
     * 是否锁定 <br/>
     * 0 未锁定 <br/>
     * 1 锁定 <br/>
     */
    private Integer isLocked;
    /**
     * 锁定时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    lockTime;
}
