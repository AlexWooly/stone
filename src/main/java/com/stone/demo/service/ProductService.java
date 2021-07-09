package com.stone.demo.service;

import com.stone.demo.model.ProductDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author NJUPT wly
 * @Date 2021/7/10 12:07 上午
 * @Version 1.0
 */

public interface ProductService {
    public List<ProductDO> list();

    public List<ProductDO> findByName(String name);

    public Set<String> findAllType();
}
