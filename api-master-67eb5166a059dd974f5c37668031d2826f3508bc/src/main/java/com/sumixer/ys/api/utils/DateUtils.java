package com.sumixer.ys.api.utils;

import java.time.LocalDateTime;

/**
 * @author : coderWu
 * @date : Created on 14:24 2018/6/14
 */
public class DateUtils {

    /**
     * 返回当前时间的是当天的第几秒
     * @param time LocalDateTime
     * @return int
     */
    public static int getDaySecond(LocalDateTime time) {
        if (null == time) {
            return 0;
        }
        return time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
    }

    /**
     * 获取当前是一周中的第几天
     * 1->周一 ... 7->周日
     * @param time LocalDateTime
     * @return int
     */
    public static int getDayOfWeek(LocalDateTime time) {
        if (null == time) {
            return 0;
        }
        return time.getDayOfWeek().getValue();
    }

}
