package com.sumixer.ys.api.web;

import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.YsGoods;
import com.sumixer.ys.api.entity.YsGoodsDiscount;
import com.sumixer.ys.api.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : coderWu
 * @date : Created on 19:49 2018/6/13
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

//    @GetMapping("/categories/{categoryId}")
//    public Result categoryGoods(@PathVariable(value = "categoryId") String categoryId) {
//        List<YsGoods> goods = goodsService.findByCategoryId(categoryId);
//        return ResultGenerator.success(goods);
//    }

    @GetMapping("/{goodsId}")
    public Result goodsDetail(@PathVariable(value = "goodsId") String goodsId) {
        YsGoods goods = goodsService.findByGoodsId(goodsId);
        YsGoodsDiscount discount = goodsService.findDiscountByGoodsId(goodsId);
        Map<String, Object> result = new HashMap<>(2);
        result.put("goods", goods);
        result.put("discount", discount);
        return ResultGenerator.success(result);
    }

    @GetMapping("/discount/{goodsId}")
    public Result goodsDiscount(@PathVariable(value = "goodsId") String goodsId) {
        YsGoodsDiscount discount = goodsService.findDiscountByGoodsId(goodsId);
        return ResultGenerator.success(discount);
    }
}
