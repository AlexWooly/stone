package com.stone.demo.service.impl;

import com.stone.demo.mapper.ProblemMapper;
import com.stone.demo.model.Problem;
import com.stone.demo.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 9:42 下午
 * @Version 1.0
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    ProblemMapper problemMapper;

    @Override
    public int problemSubmit(Problem problem) {
        return problemMapper.insert(problem);
    }
}
