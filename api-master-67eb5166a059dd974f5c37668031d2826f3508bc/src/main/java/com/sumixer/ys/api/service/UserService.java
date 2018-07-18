package com.sumixer.ys.api.service;

import com.sumixer.ys.api.dao.YsUserMapper;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.entity.YsUserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 9:18 2018/6/13
 */
@Service
public class UserService {

    @Autowired
    private YsUserMapper ysUserMapper;

    public void save(YsUser ysUser) {
        ysUserMapper.insert(ysUser);
    }

    public void update(YsUser ysUser) {
        ysUserMapper.updateByPrimaryKey(ysUser);
    }

    public YsUser findByUserId(String userid) {
        if (userid == null) {
            return null;
        }
        YsUserExample example = new YsUserExample();
        YsUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userid);
        List<YsUser> users = ysUserMapper.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    public YsUser findByOpenid(String openid) {
        if (StringUtils.isEmpty(openid)) {
            return null;
        }
        YsUserExample ysUserExample = new YsUserExample();
        YsUserExample.Criteria criteria = ysUserExample.createCriteria();
        criteria.andOpenidEqualTo(openid);
        List<YsUser> ysUser = ysUserMapper.selectByExample(ysUserExample);
        return ysUser.size() > 0 ? ysUser.get(0) : null;
    }

    public YsUser findByPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return null;
        }
        YsUserExample ysUserExample = new YsUserExample();
        YsUserExample.Criteria criteria = ysUserExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<YsUser> ysUser = ysUserMapper.selectByExample(ysUserExample);
        return ysUser.size() > 0 ? ysUser.get(0) : null;
    }

}
