package com.dofast.module.channel.service.shop;

import cn.hutool.core.lang.hash.Hash;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.shop.vo.*;
import com.dofast.module.channel.convert.shop.ShopConvert;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import com.dofast.module.channel.dal.mysql.shop.ShopMapper;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.SHOP_NOT_EXISTS;

/**
 * 店铺授权 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public String createShop(ShopCreateReqVO createReqVO) {
        // 插入
        ShopDO shop = ShopConvert.INSTANCE.convert(createReqVO);
        if (shop.getBankCard()==null){
            shop.setBankCard(createReqVO.getBankCard());
        }
        shopMapper.insert(shop);
        // 返回
        return shop.getId();
    }

    @Override
    public void updateShop(ShopUpdateReqVO updateReqVO) {
        // 校验存在
        validateShopExists(updateReqVO.getId());
        // 更新
        ShopDO updateObj = ShopConvert.INSTANCE.convert(updateReqVO);
        if (updateObj.getBankCard()==null &&updateReqVO.getBankCard()!=null){
            updateObj.setBankCard(updateReqVO.getBankCard());
        }
        shopMapper.updateById(updateObj);
    }

    @Override
    public void deleteShop(String id) {
        // 校验存在
        validateShopExists(id);
        // 删除
        shopMapper.deleteById(id);
    }

    private void validateShopExists(String id) {
        if (shopMapper.selectById(id) == null) {
            throw exception(SHOP_NOT_EXISTS);
        }
    }

    @Override
    public ShopDO getShop(String id) {
        return shopMapper.selectById(id);
    }

    @Override
    public List<ShopDO> getShopList() {
        return shopMapper.selectList();
    }

    @Override
    public PageResult<ShopDO> getShopPage(ShopPageReqVO pageReqVO) {
        return shopMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopDO> getShopList(ShopExportReqVO exportReqVO) {
        return shopMapper.selectList(exportReqVO);
    }

    @Override
    public ShopDO getShopByShopCode(String shopCode) {
       return shopMapper.selectOne("shop_code", shopCode);
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Integer getTenantIdByShopCode(String shopCode) {
        Integer tenantId = null;
        String key = "CHANNEL_SHOP_" + shopCode;
        Object val = redisTemplate.opsForValue().get(key);
        if (val != null) {
            tenantId = Integer.valueOf(val.toString());
            return tenantId;
        }
        ShopDO shopDO = getShopByShopCode(shopCode);
        if (shopDO != null) {
            tenantId =  shopDO.getTenantId();
            redisTemplate.opsForValue().set("CHANNEL_SHOP_" + shopCode, tenantId);
            return tenantId;
        }
        return -1;
    }

    @Override
    public List<ChannelShopVO> getChannelShops() {
        //存储渠道和对应渠道下的店铺的poseCode
        HashMap<String, List<ChannelReqShopVO>> channelMap = new HashMap<String, List<ChannelReqShopVO>>();
        List<ShopDO> shopDOList = shopMapper.selectList();
        for(int i=0; i<shopDOList.size(); i++){
            ShopDO shopDO = shopDOList.get(i);
            if((channelMap.get(shopDO.getChannel())) == null){
                List<ChannelReqShopVO> list = new ArrayList<>();
                ChannelReqShopVO channelReqShopVO= new ChannelReqShopVO();
                channelReqShopVO.setShopCode(shopDO.getShopCode());
                channelReqShopVO.setDisplayName(shopDO.getDisplayName());
                list.add(channelReqShopVO);
                channelMap.put(shopDO.getChannel(), list);
            }else{
                List<ChannelReqShopVO> list = channelMap.get(shopDO.getChannel());
                ChannelReqShopVO channelReqShopVO= new ChannelReqShopVO();
                channelReqShopVO.setShopCode(shopDO.getShopCode());
                channelReqShopVO.setDisplayName(shopDO.getDisplayName());
                list.add(channelReqShopVO);
                channelMap.put(shopDO.getChannel(), list);
            }
        }
        //返回给前端的树形数据
        List<ChannelShopVO> channelShopVOList = new ArrayList<>();
        for (Map.Entry<String, List<ChannelReqShopVO>> entry : channelMap.entrySet()) {
            ChannelShopVO channelShopVO = new ChannelShopVO();
            channelShopVO.setChannelName(entry.getKey());
            channelShopVO.setChannelReqShopVOList(entry.getValue());
            channelShopVOList.add(channelShopVO);
        }
        return channelShopVOList;
    }
}
