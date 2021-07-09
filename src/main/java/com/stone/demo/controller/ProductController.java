package com.stone.demo.controller;

import com.stone.demo.service.ProductService;
import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 12:19 上午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 获取全部商品
     * @return 全部商品
     */
    @GetMapping("/list")
    public JsonData getList(){
        return JsonData.buildSuccess(productService.list());
    }

    /**
     * 查找所有商品type
     * @return 所有商品type
     */
    @GetMapping("/findAllType")
    public JsonData findAllType(){
        return JsonData.buildSuccess(productService.findAllType());
    }

    /**
     * 模糊匹配商品
     * @param name 模糊名称
     * @return  商品
     */
    @GetMapping("findProduct")
    public JsonData findProduct(@RequestParam("name") String name){
        return JsonData.buildSuccess(productService.findByName(name));
    }

}
