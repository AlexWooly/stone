package com.stone.demo.controller;

import com.stone.demo.model.MessageDO;
import com.stone.demo.service.MessageService;
import com.stone.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author NJUPT wly
 * @Date 2021/7/13 2:13 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/msg")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping("/page")
    public JsonData page(@RequestParam("page") int page,@RequestParam("size") int size){
        Map<String,Object> map = messageService.page(page,size);
        return JsonData.buildSuccess(map);
    }

    @RequestMapping("/report")
    public JsonData save(@RequestBody MessageDO messageDO){
        return JsonData.buildSuccess(messageService.report(messageDO));
    }

    @DeleteMapping("delete")
    public JsonData dele(@RequestParam("id") int id){
        return JsonData.buildSuccess(messageService.deleteByBatch(id));
    }

    @GetMapping ("all")
    public JsonData getAll(){
        return JsonData.buildSuccess(messageService.list());
    }
}
