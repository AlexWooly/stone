package com.stone.demo.wechat.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@TableName("user")
public class User implements Serializable {

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 性别 0-未知 1-male,2-female
     */
    private Integer gender;

    /**
     * 头像地址
     */
    private String avatarUrl;

    private String unionId;

    private String country;

    private String province;

    private String city;

    private String language;

    private String email;

    private String phone;

    private String remarks;

    private int enabled;

    @JsonIgnore
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +

                ", nickName='" + nickName + '\'' +

                '}';
    }
}
