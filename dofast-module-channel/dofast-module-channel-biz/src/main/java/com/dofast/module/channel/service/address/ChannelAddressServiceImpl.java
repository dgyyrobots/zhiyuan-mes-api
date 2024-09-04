package com.dofast.module.channel.service.address;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressExportReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressPageReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressUpdateReqVO;
import com.dofast.module.channel.convert.address.AddressConvert;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import com.dofast.module.channel.dal.mysql.address.ChannelAddressMapper;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.ADDRESS_NOT_EXISTS;

/**
 * 交易客户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChannelAddressServiceImpl implements ChannelAddressService {

    @Resource
    private ChannelAddressMapper channelAddressMapper;

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public Integer createAddress(AddressCreateReqVO createReqVO) {
        // 插入
        ChannelAddressDO address = AddressConvert.INSTANCE.convert(createReqVO);
        channelAddressMapper.insert(address);
        // 返回

        return address.getId();
    }

    @Override
    public void updateAddress(AddressUpdateReqVO updateReqVO) {
        // 校验存在
        validateAddressExists(updateReqVO.getId());
        // 更新
        ChannelAddressDO updateObj = AddressConvert.INSTANCE.convert(updateReqVO);
        channelAddressMapper.updateById(updateObj);
    }

    @Override
    public void deleteAddress(Integer id) {
        // 校验存在
        validateAddressExists(id);
        // 删除
        channelAddressMapper.deleteById(id);
    }

    private void validateAddressExists(Integer id) {
        if (channelAddressMapper.selectById(id) == null) {
            throw exception(ADDRESS_NOT_EXISTS);
        }
    }

    @Override
    public ChannelAddressDO getAddress(Integer id) {
        return channelAddressMapper.selectById(id);
    }

    @Override
    public ChannelAddressDO getAddress(String field1, Object val1){
        return channelAddressMapper.selectOne(field1, val1);
    }

    @Override
    public ChannelAddressDO getAddress(String field1, Object val1, String field2, Object val2){
        return channelAddressMapper.selectOne(field1,  val1,  field2,  val2);
    }

    @Override
    public List<ChannelAddressDO> getAddressList(Collection<Integer> ids) {
        return channelAddressMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ChannelAddressDO> getAddressPage(AddressPageReqVO pageReqVO) {
        return channelAddressMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ChannelAddressDO> getAddressList(AddressExportReqVO exportReqVO) {
        return channelAddressMapper.selectList(exportReqVO);
    }

}
