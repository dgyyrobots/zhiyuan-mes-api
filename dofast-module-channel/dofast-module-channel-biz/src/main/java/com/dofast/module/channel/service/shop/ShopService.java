package com.dofast.module.channel.service.shop;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.shop.vo.*;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

/**
 * 店铺授权 Service 接口
 *
 * @author 芋道源码
 */
public interface ShopService {

    /**
     * 创建店铺授权
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createShop(@Valid ShopCreateReqVO createReqVO);

    /**
     * 更新店铺授权
     *
     * @param updateReqVO 更新信息
     */
    void updateShop(@Valid ShopUpdateReqVO updateReqVO);

    /**
     * 删除店铺授权
     *
     * @param id 编号
     */
    void deleteShop(String id);

    /**
     * 获得店铺授权
     *
     * @param id 编号
     * @return 店铺授权
     */
    ShopDO getShop(String id);

    /**
     * 获得店铺授权列表
     *
     * @return 店铺授权列表
     */
    List<ShopDO> getShopList();

    /**
     * 获得店铺授权分页
     *
     * @param pageReqVO 分页查询
     * @return 店铺授权分页
     */
    PageResult<ShopDO> getShopPage(ShopPageReqVO pageReqVO);

    /**
     * 获得店铺授权列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 店铺授权列表
     */
    List<ShopDO> getShopList(ShopExportReqVO exportReqVO);

    /**
     *
     */
    ShopDO getShopByShopCode(String posCode);

    Integer getTenantIdByShopCode(String shopCode);

    /**
     * @return 所有渠道和其渠道里的店铺列表
     */
    List<ChannelShopVO> getChannelShops();


}
