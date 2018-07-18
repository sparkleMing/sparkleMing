package com.sumixer.ys.api.web;

import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.*;
import com.sumixer.ys.api.service.GoodsService;
import com.sumixer.ys.api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 20:31 2018/6/13
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;


    @GetMapping("/near")
    public Result nearbyStore(@RequestParam(value = "lnt") String lnt,
                              @RequestParam(value = "lat") String lat) {
        List<YsStore> stores = storeService.findByLocation(lnt, lat);
        return ResultGenerator.success(stores);
    }

    @GetMapping("/{storeId}")
    public Result storeDetail(@PathVariable(value = "storeId") String storeId) {
        HashMap<String, Object> results = new HashMap<>(4);
        YsStore store = storeService.findByStoreId(storeId);
        if (null == store) {
            return ResultGenerator.success();
        }
        boolean open = storeService.isWeekDayOpen(store.getOpenDay(), store.getOpenBeginTime(), store.getOpenEndTime())
                && !storeService.isInVacation(storeId);
        if (open) {
            results.put("store", store);
            results.put("notices", storeService.findAllNotice(storeId));
            results.put("menus", storeService.findAllMenu(storeId));
            results.put("slides", storeService.findAllSlides(storeId));
        }
        results.put("open", open);
        return ResultGenerator.success(results);
    }

    @GetMapping("/open/{storeId}")
    public Result isOpen(@PathVariable(value = "storeId") String storeId) {
        boolean open = storeService.isStoreOpen(storeId);
        return ResultGenerator.success(open);
    }

    @GetMapping("/menus/{storeId}")
    public Result menus(@PathVariable(value = "storeId") String storeId) {
        List<YsStoreMenu> menus = storeService.findAllMenu(storeId);
        return ResultGenerator.success(menus);
    }

    @GetMapping("/notices/{storeId}")
    public Result notices(@PathVariable(value = "storeId") String storeId) {
        List<YsStoreNotice> notices = storeService.findAllNotice(storeId);
        return ResultGenerator.success(notices);
    }

    @GetMapping("/slides/{storeId}")
    public Result slides(@PathVariable(value = "storeId") String storeId) {
        List<YsWxStoreSlide> storeSlides = storeService.findAllSlides(storeId);
        return ResultGenerator.success(storeSlides);
    }

    @GetMapping("/{storeId}/category/{categoryId}")
    public Result goods(@PathVariable(value = "storeId") String storeId,
                        @PathVariable(value = "categoryId") String categoryId) {
        List<YsGoods> goods = goodsService.findStoreGoodsByCategoryId(storeId, categoryId);
        return ResultGenerator.success(goods);
    }

    @GetMapping("/{storeId}/keyword/{keyword}")
    public Result search(@PathVariable(value = "storeId") String storeId,
                        @PathVariable(value = "keyword") String keyword) {
        List<YsGoods> goods = goodsService.storeSearch(storeId, keyword);
        return ResultGenerator.success(goods);
    }


}
