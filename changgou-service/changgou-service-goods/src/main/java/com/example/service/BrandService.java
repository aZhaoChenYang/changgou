package com.example.service;

import com.example.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     */
    List<Brand> findAll();

    Brand findById(Integer id);
}
