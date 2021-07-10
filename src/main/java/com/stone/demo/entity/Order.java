package com.stone.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 2:55 下午
 * @Version 1.0
 */
@Data
public class Order implements Serializable {
    private List<Map<String,Integer>> list;
}
