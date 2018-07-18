package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.YsUserAddressMapper;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.entity.YsUserAddress;
import com.sumixer.ys.api.entity.YsUserAddressExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 16:50 2018/6/14
 */
@Service
public class UserAddressService {

    @Autowired
    YsUserAddressMapper ysUserAddressMapper;

    /**
     * 根据用户id查找所有的地址
     * @param userid String userid
     * @return List<YsUserAddress>
     */
    public List<YsUserAddress> findAllAddress(String userid) {
        if (StringUtils.isEmpty(userid)) {
            return null;
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userid);
        return ysUserAddressMapper.selectByExample(example);
    }

    /**
     * 根据地址id查询具体信息
     * @param addressId String addressid
     * @return YsUserAddress
     */
    public YsUserAddress findById(String addressId) {
        if (StringUtils.isEmpty(addressId)) {
            return null;
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIdEqualTo(addressId);
        List<YsUserAddress> addresses = ysUserAddressMapper.selectByExample(example);
        return addresses.size() > 0 ? addresses.get(0) : null;
    }

    /**
     * 根据用户id和地址id获取地址信息
     * 双重id查询用于更新或删除时判断地址是否存在，并且是自己的地址，不是别人的地址
     * @param userId String
     * @param addressId String
     * @return YsUserAddress
     */
    public YsUserAddress findByUserIdAndAddressId(String userId, String addressId) {
        if (StringUtils.isAnyEmpty(addressId, userId)) {
            return null;
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIdEqualTo(addressId).andUserIdEqualTo(userId);
        List<YsUserAddress> addresses = ysUserAddressMapper.selectByExample(example);
        return addresses.size() > 0 ? addresses.get(0) : null;
    }

    /**
     * 添加新的地址
     * @param address YsUserAddress
     */
    public void save(YsUserAddress address) {
        if (address == null) {
            return;
        }
        //如果设置为默认地址先取消已设置的默认地址状态
        if (address.getIsDefault() == Constants.DEFAULT_ADDRESS) {
            removeDefault(address.getUserId());
        }
        ysUserAddressMapper.insert(address);
    }

    /**
     * 更新地址
     * @param address YsUserAddress
     */
    public void update(YsUserAddress address) {
        if (address == null || StringUtils.isEmpty(address.getAddressId())) {
            return;
        }
        //如果设置为默认地址先取消已设置的默认地址状态
        if (address.getIsDefault() != null && address.getIsDefault() == Constants.DEFAULT_ADDRESS) {
            removeDefault(address.getUserId());
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIdEqualTo(address.getAddressId());
        ysUserAddressMapper.updateByExampleSelective(address, example);
    }

    /**
     * 取消用户设置的默认地址
     * @param userid String
     */
    public void removeDefault(String userid) {
        if (StringUtils.isEmpty(userid)) {
            return;
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andIsDefaultEqualTo(Constants.DEFAULT_ADDRESS)
        .andUserIdEqualTo(userid);
        YsUserAddress otherAddress = new YsUserAddress();
        otherAddress.setIsDefault(Constants.NOT_DEFAULT_ADDRESS);
        ysUserAddressMapper.updateByExampleSelective(otherAddress, example);
    }

    public void delete(String userId, String addressId) {
        if (StringUtils.isAnyEmpty(userId, addressId)) {
            return;
        }
        YsUserAddressExample example = new YsUserAddressExample();
        YsUserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andAddressIdEqualTo(addressId);
        ysUserAddressMapper.deleteByExample(example);
    }

}
