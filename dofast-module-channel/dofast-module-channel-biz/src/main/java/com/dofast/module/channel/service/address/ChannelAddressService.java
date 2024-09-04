package com.dofast.module.channel.service.address;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.address.vo.AddressUpdateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressExportReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressPageReqVO;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;

import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

/**
 * 交易客户 Service 接口
 *
 * @author 芋道源码
 */
public interface ChannelAddressService {

    /**
     * 创建交易客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createAddress(@Valid AddressCreateReqVO createReqVO);

    /**
     * 更新交易客户
     *
     * @param updateReqVO 更新信息
     */
    void updateAddress(@Valid AddressUpdateReqVO updateReqVO);

    /**
     * 删除交易客户
     *
     * @param id 编号
     */
    void deleteAddress(Integer id);

    /**
     * 获得交易客户
     *
     * @param id 编号
     * @return 交易客户
     */
    ChannelAddressDO getAddress(Integer id);
    ChannelAddressDO getAddress(String field1, Object val1);
    ChannelAddressDO getAddress(String field1, Object val1, String field2, Object val2);
    /**
     * 获得交易客户列表
     *
     * @param ids 编号
     * @return 交易客户列表
     */
    List<ChannelAddressDO> getAddressList(Collection<Integer> ids);

    /**
     * 获得交易客户分页
     *
     * @param pageReqVO 分页查询
     * @return 交易客户分页
     */
    PageResult<ChannelAddressDO> getAddressPage(AddressPageReqVO pageReqVO);

    /**
     * 获得交易客户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 交易客户列表
     */
    List<ChannelAddressDO> getAddressList(AddressExportReqVO exportReqVO);

}
