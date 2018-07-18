package com.sumixer.ys.api.web;

import com.sumixer.ys.api.annotation.LoginUser;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.entity.YsUserAddress;
import com.sumixer.ys.api.service.UserAddressService;
import com.sumixer.ys.api.utils.RandomUtils;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 16:10 2018/6/14
 */
@RestController
@RequestMapping("/addresses")
@Transactional
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;

    @GetMapping("/")
    public Result addressList(@LoginUser YsUser ysUser) {
        List<YsUserAddress> addresses = userAddressService.findAllAddress(ysUser.getUserId());
        return ResultGenerator.success(addresses);
    }


    @PostMapping("/")
    public Result newAddress(@LoginUser YsUser user,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             @RequestParam("lat") String lat,
                             @RequestParam("lnt") String lnt,
                             @RequestParam("default") Integer isDefault) {
        boolean illegal = StringUtils.isAnyEmpty(name.trim(), phone.trim(), address.trim(), lat.trim(), lnt.trim()) ||
                !(Constants.DEFAULT_ADDRESS == isDefault || Constants.NOT_DEFAULT_ADDRESS == isDefault);
        if (illegal) {
            return ResultGenerator.fail("参数错误");
        }
        YsUserAddress ysUserAddress = new YsUserAddress();
        ysUserAddress.setName(name);
        ysUserAddress.setPhone(phone);
        ysUserAddress.setAddress(address);
        ysUserAddress.setLocation(lnt + "," + lat);
        ysUserAddress.setIsDefault(isDefault);
        ysUserAddress.setUserId(user.getUserId());
        ysUserAddress.setAddressId(RandomUtils.string("address", ysUserAddress.getLocation()));
        userAddressService.save(ysUserAddress);
        return ResultGenerator.success("添加成功");
    }

    @PutMapping("/{addressId}")
    public Result editAddress(@LoginUser YsUser user,
                             @PathVariable(value = "addressId") String addressId,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "lat", required = false) String lat,
                             @RequestParam(value = "lnt", required = false) String lnt,
                             @RequestParam(value = "default", required = false) Integer isDefault) {
        boolean usefulAddress = isDefault != null && !(Constants.DEFAULT_ADDRESS == isDefault ||
                Constants.NOT_DEFAULT_ADDRESS == isDefault);
        if (usefulAddress) {
            return ResultGenerator.fail("默认地址参数错误");
        }
        YsUserAddress originalAddress = userAddressService.findByUserIdAndAddressId(user.getUserId(), addressId);
        if (null == originalAddress) {
            return ResultGenerator.fail("地址不存在");
        }
        originalAddress.setName(name);
        originalAddress.setPhone(phone);
        originalAddress.setAddress(address);
        originalAddress.setLocation(lnt + "," + lat);
        originalAddress.setIsDefault(isDefault);
        originalAddress.setUserId(user.getUserId());
        userAddressService.update(originalAddress);
        return ResultGenerator.success("修改成功");
    }

    @DeleteMapping("/{addressId}")
    public Result delete(@LoginUser YsUser user,
                         @PathVariable(value = "addressId") String addressId) {
        userAddressService.delete(user.getUserId(), addressId);
        return ResultGenerator.success("删除成功");
    }
}
