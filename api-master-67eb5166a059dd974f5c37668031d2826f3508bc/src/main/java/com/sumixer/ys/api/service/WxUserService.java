package com.sumixer.ys.api.service;

import com.sumixer.ys.api.dao.YsWxUserMapper;
import com.sumixer.ys.api.entity.YsWxUser;
import com.sumixer.ys.api.entity.YsWxUserExample;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 10:12 2018/6/13
 */
@Service
public class WxUserService {

    @Autowired
    YsWxUserMapper ysWxUserMapper;

    public void save(YsWxUser ysWxUser) {
        if (ysWxUser == null || StringUtils.isEmpty(ysWxUser.getOpenid())) {
            return;
        }
        ysWxUserMapper.insert(ysWxUser);
    }


    public void update(YsWxUser ysWxUser) {
        if (ysWxUser == null || StringUtils.isEmpty(ysWxUser.getOpenid())) {
            return;
        }
        ysWxUser.setNickname(EmojiParser.parseToHtmlDecimal(ysWxUser.getNickname()));

        YsWxUserExample ysWxUserExample = new YsWxUserExample();
        YsWxUserExample.Criteria criteria = ysWxUserExample.createCriteria();
        criteria.andOpenidEqualTo(ysWxUser.getOpenid());
        ysWxUserMapper.updateByExample(ysWxUser, ysWxUserExample);
    }


    public YsWxUser findByOpenid(String openid) {
        if (StringUtils.isEmpty(openid)) {
            return null;
        }
        YsWxUserExample ysWxUserExample = new YsWxUserExample();
        YsWxUserExample.Criteria criteria = ysWxUserExample.createCriteria();
        criteria.andOpenidEqualTo(openid);
        List<YsWxUser> ysWxUserOrigin = ysWxUserMapper.selectByExample(ysWxUserExample);
        return ysWxUserOrigin.size() > 0 ? ysWxUserOrigin.get(0) : null;
    }

    /**
     * 更新或添加，如果有则更新，没有添加
     * @param ysWxUser YsWxUser
     */
    public void updateOrAdd(YsWxUser ysWxUser) {
        if (ysWxUser == null) {
            return;
        }
        YsWxUser originYsWxUser = findByOpenid(ysWxUser.getOpenid());
        if (null == originYsWxUser) {
            save(ysWxUser);
        } else {
            ysWxUser.setId(originYsWxUser.getId());
            update(ysWxUser);
        }
    }
}
