package com.sumixer.ys.api.config;

/**
 * @author : coderWu
 * @date : Created on 19:21 2018/6/12
 */
public class Constants {

    public static final String DEV_ENV = "dev";

    //"47.106.121.106"
    public static final String DOMAIN = "api.yisongbld.com";

    public static int TOKEN_EXPIRE_HOUR = 1;

    //短信验证码发送间隔，有限时间
    public static int MESSAGE_CODE_SECONDS = 60;
    //短信验证码长度
    public static int MESSAGE_CODE_LENGTH = 6;

    public static final String TOKEN_KEY = "Ys-user";

    public static final String INNER_USER_ID = "user-id";

    //用户状态
    public static final int YS_USER_NORMAL = 1;
    public static final int YS_USER_FROZEN = 2;

    public static final String YS_GOODS_CATEGORY_RANK_FIRST = "一级分类";
    public static final String YS_GOODS_CATEGORY_RANK_SECOND = "二级分类";

    public static final char WEEK_DAY_OPEN = '1';
    public static final char WEEK_DAY_CLOSED = '0';

    //是否默认地址
    public static final int DEFAULT_ADDRESS = 1;
    public static final int NOT_DEFAULT_ADDRESS = 2;

    //订单支付方式
    public static final int ONLINE_PAY = 1;
    public static final int ARRIVED_PAY = 2;
    public static final int WALLET_PAY = 3;

    //订单支付状态
    public static final int PAY_ORDER = 1;
    public static final int NOT_PAY_ORDER = 2;
    public static final int CANCLE_PAY = 3;
    public static final int ARRIVED_PAY_STATUS = 4;
    public static final int REFUND_ORDER = 5;
    public static final int REQUEST_REFUND_ORDER = 5;

    //订单发货状态
    public static final int NOT_CONFIRM_ORDER = 1;
    public static final int NOT_SEND_ORDER = 2;
    public static final int CONFIRMED_ORDER = 3;

    public static final String WEPAY_OK = "OK";

    public static final String WEPAY_SUCCESS = "SUCCESS";
    public static final String WEPAY_FAIL = "FAIL";
    public static final String WEPAY_NOTIFY_SUCCESS = "SUCCESS";
    public static final String REFUND_NOTIFY_SUCCESS = "SUCCESS";
    public static final String WEPAY_NOTIFY_FAIL = "FAIL";

    public static final int ONLINE_PRINT_MACHINE = 1;

    //正常使用门店
    public static final int NORMAL_STORE = 1;

    //商品状态
    public static final int PUBLISHED_GOODS = 1;
    public static final int NOT_PUBLISH_GOODS = 2;

}
