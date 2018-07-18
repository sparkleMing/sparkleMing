package com.sumixer.ys.api.web;

import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.YsGoodsCategory;
import com.sumixer.ys.api.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 19:09 2018/6/13
 */
@RestController
@RequestMapping("/categories")
public class GoodsCategoryController {


    @Autowired
    GoodsCategoryService goodsCategoryService;

    @GetMapping("/rank/first")
    public Result allCategories() {
        System.out.println("emmmmmmmmm");
        List<YsGoodsCategory> rankFirst = goodsCategoryService.rankFirst();
        return ResultGenerator.success(rankFirst);
    }

    @GetMapping("/rank/second/{parentId}")
    public Result allCategories(@PathVariable(value = "parentId") String parentId) {
        System.out.println(parentId);
        List<YsGoodsCategory> rankSecond = goodsCategoryService.subCategory(parentId);
        System.out.println(parentId);
        return ResultGenerator.success(rankSecond);
    }
}
