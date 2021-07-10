package com.stone.demo.service.impl;

import com.stone.demo.mapper.WorkSubmitMapper;
import com.stone.demo.model.WorkSubmit;
import com.stone.demo.service.WorkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 9:43 下午
 * @Version 1.0
 */
@Service
public class WorkSubmitServiceImpl implements WorkSubmitService {
    @Autowired
    WorkSubmitMapper workSubmitMapper;

    @Override
    public int submit(WorkSubmit workSubmit) {
        return workSubmitMapper.insert(workSubmit);
    }
}
