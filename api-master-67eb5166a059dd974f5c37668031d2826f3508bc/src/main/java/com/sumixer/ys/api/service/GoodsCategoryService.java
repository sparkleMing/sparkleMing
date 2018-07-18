package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.YsGoodsCategoryMapper;
import com.sumixer.ys.api.entity.YsGoodsCategory;
import com.sumixer.ys.api.entity.YsGoodsCategoryExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类service
 * @author : coderWu
 * @date : Created on 19:20 2018/6/13
 */
@Service
public class GoodsCategoryService {

    @Autowired
    YsGoodsCategoryMapper ysGoodsCategoryMapper;


    /**
     * 获取所有一级分类
     * @return List<YsGoodsCategory>
     */
    public List<YsGoodsCategory> rankFirst() {
        YsGoodsCategoryExample goodsCategoryExample = new YsGoodsCategoryExample();
        YsGoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andRankEqualTo(Constants.YS_GOODS_CATEGORY_RANK_FIRST);
        return ysGoodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 根据一级分类id获取对应耳机分类
     * @param id 一级分类id
     * @return List<YsGoodsCategory>
     */
    public List<YsGoodsCategory> subCategory(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        YsGoodsCategoryExample goodsCategoryExample = new YsGoodsCategoryExample();
        YsGoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andRankEqualTo(Constants.YS_GOODS_CATEGORY_RANK_SECOND)
                .andParentIdEqualTo(id);
        return ysGoodsCategoryMapper.selectByExample(goodsCategoryExample);
    }
}
