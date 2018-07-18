package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.YsGoodsDiscountMapper;
import com.sumixer.ys.api.dao.YsGoodsMapper;
import com.sumixer.ys.api.dao.YsStoreGoodsMapper;
import com.sumixer.ys.api.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品service
 * @author : coderWu
 * @date : Created on 19:50 2018/6/13
 */
@Service
public class GoodsService {

    @Autowired
    YsGoodsMapper ysGoodsMapper;
    @Autowired
    YsGoodsDiscountMapper ysGoodsDiscountMapper;
    @Autowired
    YsStoreGoodsMapper ysStoreGoodsMapper;

    /**
     * 获取所有的发布状态（上架）的仓库中的商品
     * @param id 二级分类id
     * @return List<YsGoods>
     */
    public List<YsGoods> findByCategoryId(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        YsGoodsExample goodsExample = new YsGoodsExample();
        YsGoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andCategoryIdEqualTo(id).andPublishEqualTo(Constants.PUBLISHED_GOODS);
        return ysGoodsMapper.selectByExample(goodsExample);
    }

    /**
     * 根据goods id获取仓库中的发布状态（上架）的商品详情
     * @param goodsId String
     * @return YsGoods
     */
    public YsGoods findByGoodsId(String goodsId) {
        if (StringUtils.isEmpty(goodsId)) {
            return null;
        }
        YsGoodsExample goodsExample = new YsGoodsExample();
        YsGoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId).andPublishEqualTo(Constants.PUBLISHED_GOODS);
        List<YsGoods> goods = ysGoodsMapper.selectByExample(goodsExample);
        return goods.size() > 0 ? goods.get(0) : null;
    }

    /**
     * 根据goods id 获取对应商品的打折状态
     * @param goodsId String goods id
     * @return YsGoodsDiscount
     */
    public YsGoodsDiscount findDiscountByGoodsId(String goodsId) {
        if (StringUtils.isEmpty(goodsId)) {
            return null;
        }
        YsGoodsDiscountExample discountExample = new YsGoodsDiscountExample();
        YsGoodsDiscountExample.Criteria criteria = discountExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId).andBeginTimeLessThan(new Date())
        .andEndTimeGreaterThan(new Date());
        //如果有多条打折信息，返回最近开始的
        discountExample.setOrderByClause("begin_time desc");
        List<YsGoodsDiscount> discounts = ysGoodsDiscountMapper.selectByExample(discountExample);
        return discounts.size() > 0 ? discounts.get(0) : null;
    }

    /**
     * 根据商品分类获取某个门店上架的商品
     * @param storeId storeID
     * @param categoryId categoryId
     * @return List<YsStoreGoods>
     */
    public List<YsGoods> findStoreGoodsByCategoryId(String storeId, String categoryId) {
        if (StringUtils.isAnyEmpty(storeId, categoryId)) {
            return null;
        }
        return ysStoreGoodsMapper.selectPublishGoodsByCategoryId(storeId, categoryId);
    }

    /**
     * 根据goods id获取某个门店上架的商品
     * @param storeId 门店id
     * @param goodsId 商品id
     * @return YsGoods
     */
    public YsGoods findStoreGoodsByGoodsId(String storeId, String goodsId) {
        if (StringUtils.isAnyEmpty(storeId, goodsId)) {
            return null;
        }
        List<YsGoods> goods = ysStoreGoodsMapper.selectPublishedGoods(storeId, goodsId);
        return goods.size() > 0 ? goods.get(0) : null;
    }

    /**
     * 根据商品名模糊搜索顶埔商品
     * @param storeId String
     * @param keyword String
     * @return List<YsGoods>
     */
    public List<YsGoods> storeSearch(String storeId, String keyword) {
        if (StringUtils.isAnyEmpty(storeId, keyword)) {
            return null;
        }
        return ysStoreGoodsMapper.searchGoods(storeId, keyword);
    }

}
