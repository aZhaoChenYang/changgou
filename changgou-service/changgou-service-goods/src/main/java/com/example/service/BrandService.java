package com.example.service;

import com.example.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     */
    List<Brand> findAll();

    Brand findById(Integer id);

    void add(Brand brand);

    void update(Brand brand);

    void delete(Integer id);

    List<Brand> findList(Brand brand);

    PageInfo<Brand> findPage(Integer page, Integer size);

    PageInfo<Brand> findPage(Brand brand ,Integer page, Integer size);

}
