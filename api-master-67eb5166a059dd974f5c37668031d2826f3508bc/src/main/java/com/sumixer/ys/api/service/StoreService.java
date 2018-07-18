package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.*;
import com.sumixer.ys.api.entity.*;
import com.sumixer.ys.api.utils.DateUtils;
import com.sumixer.ys.api.utils.LocationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 21:38 2018/6/13
 */
@Service
public class StoreService {

    @Autowired
    YsStoreMapper ysStoreMapper;
    @Autowired
    YsStoreCloseTimeMapper ysStoreCloseTimeMapper;
    @Autowired
    YsStoreNoticeMapper ysStoreNoticeMapper;
    @Autowired
    YsStoreMenuMapper ysStoreMenuMapper;
    @Autowired
    YsStoreGoodsMapper ysStoreGoodsMapper;
    @Autowired
    YsGoodsMapper ysGoodsMapper;
    @Autowired
    YsWxStoreSlideMapper ysWxStoreSlideMapper;

    /**
     * 根据经纬度获取在配送范围内且没有闭店的门店
     * @param lnt 经度
     * @param lat 纬度
     * @return List<YsStore>
     */
    public List<YsStore> findByLocation(String lnt, String lat) {
        if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lnt)) {
            return new ArrayList<>();
        }
        YsStoreExample ysStoreExample = new YsStoreExample();
        YsStoreExample.Criteria criteria = ysStoreExample.createCriteria();
        criteria.andLocaltionIsNotNull().andPathGreaterThan(0.00)
        .andStatusEqualTo(Constants.NORMAL_STORE);
        List<YsStore> stores = ysStoreMapper.selectByExample(ysStoreExample);
        List<YsStore> results = new ArrayList<>(stores.size());
        String storeLat, storeLnt;
        String[] location;
        for (YsStore store : stores) {
            location = store.getLocaltion().split(",");
            if (2 == location.length) {
                storeLnt = location[0];
                storeLat = location[1];
                //1.在配送范围
                //2.在营业时间
                //3.不在假期
                //不在假期放在最后判断，减少数据库查询
                if (LocationUtils.getDistance(lnt, lat, storeLnt, storeLat) <= store.getPath() &&
                        isWeekDayOpen(store.getOpenDay(), store.getOpenBeginTime(), store.getOpenEndTime()) &&
                                !isInVacation(store.getStoreId())) {
                    results.add(store);
                }
            }
        }
        return results;
    }


    /**
     * 根据storeId获取store具体信息
     * @param storeId storeId
     * @return YsStore
     */
    public YsStore findByStoreId(String storeId) {
        if (StringUtils.isEmpty(storeId)) {
            return null;
        }
        YsStoreExample example = new YsStoreExample();
        YsStoreExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId).andStatusEqualTo(Constants.NORMAL_STORE);
        List<YsStore> store = ysStoreMapper.selectByExample(example);
        return store.size() > 0 ? store.get(0) : null;
    }

    /**
     * 判断store是否营业
     * @param storeId storeId
     * @return boolean
     */
    public boolean isStoreOpen(String storeId) {
        if (StringUtils.isEmpty(storeId)) {
            return false;
        }
        YsStore ysStore = findByStoreId(storeId);
        if (ysStore == null || !isWeekDayOpen(ysStore.getOpenDay(), ysStore.getOpenBeginTime(), ysStore.getOpenEndTime())) {
            return false;
        }
        return !isInVacation(storeId);
    }

    public List<YsStoreMenu> findAllMenu(String storeId) {
        if (StringUtils.isEmpty(storeId)) {
            return null;
        }
        YsStoreMenuExample example = new YsStoreMenuExample();
        YsStoreMenuExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId);
        List<YsStoreMenu> menus = ysStoreMenuMapper.selectByExample(example);
        YsGoodsExample goodsExample ;
        YsGoodsExample.Criteria goodsCriteria;
        if (menus != null) {
            for (YsStoreMenu menu : menus) {
                if (menu.getGoodsId() == null) {
                    continue;
                }
                List<String> goodsIdList = Arrays.asList(menu.getGoodsId().split(","));
                goodsExample = new YsGoodsExample();
                goodsCriteria = goodsExample.createCriteria();
                goodsCriteria.andGoodsIdIn(goodsIdList);
                menu.setGoodsList(ysGoodsMapper.selectByExample(goodsExample));
            }
        }
        return menus;
    }

    public List<YsStoreNotice> findAllNotice(String storeId) {
        if (StringUtils.isEmpty(storeId)) {
            return null;
        }
        YsStoreNoticeExample example = new YsStoreNoticeExample();
        YsStoreNoticeExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId);
        return ysStoreNoticeMapper.selectByExample(example);
    }

    public List<YsWxStoreSlide> findAllSlides(String storeId) {
        if (StringUtils.isEmpty(storeId)) {
            return null;
        }
        YsWxStoreSlideExample example = new YsWxStoreSlideExample();
        YsWxStoreSlideExample.Criteria criteria = example.createCriteria();
        criteria.andYsStoreIdEqualTo(storeId);
        return ysWxStoreSlideMapper.selectByExample(example);
    }

    /**
     * 按周几判断是否营业
     * @param week String 形如 1111111这样长度为七的字符串，1代表当天营业
     * @return boolean
     */
    public boolean isWeekDayOpen(String week, Integer openBeginTime, Integer openEndTime) {
        int weekDay = DateUtils.getDayOfWeek(LocalDateTime.now());
        int seconds = DateUtils.getDaySecond(LocalDateTime.now());
        return week.length() >= weekDay &&
                Constants.WEEK_DAY_OPEN == week.charAt(weekDay - 1) &&
                ((openBeginTime / 1000) <= seconds && (openEndTime / 1000) >= seconds);
    }

    /**
     * 判断是否在节假日
     * @param storeId storeid
     * @return boolean
     */
    public boolean isInVacation(String storeId) {
        Date date = new Date();
        YsStoreCloseTimeExample example = new YsStoreCloseTimeExample();
        YsStoreCloseTimeExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId).andBeginTimeLessThan(date).andEndTimeGreaterThan(date);
        List<YsStoreCloseTime> closeTimes = ysStoreCloseTimeMapper.selectByExample(example);
        return closeTimes.size() > 0;
    }

}
