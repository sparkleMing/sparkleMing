package com.sumixer.ys.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumixer.ys.api.entity.YsOrderItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 9:17 2018/6/16
 */
public class JSONUtils {


    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);


    public static List<YsOrderItem> readOrderItems(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return new ArrayList<>();
        }
        List<YsOrderItem> result = new ArrayList<>();
        try {
            JSONArray jsonArray = JSON.parseArray(jsonString);
            JSONObject object;
            for (int i = 0, l = jsonArray.size(); i < l; i++) {
                object = jsonArray.getJSONObject(i);
                if (!object.containsKey("goodsId") || !object.containsKey("count")) {
                    return new ArrayList<>();
                }
                YsOrderItem item = new YsOrderItem();
                item.setGoodsId(object.getString("goodsId"));
                item.setCount(object.getDouble("count"));
                result.add(item);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }


}
