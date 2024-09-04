package com.dofast.module.channel.service.address;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.framework.test.core.util.RandomUtils;
import com.dofast.module.channel.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressExportReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressPageReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import com.dofast.module.channel.dal.mysql.address.ChannelAddressMapper;
import java.util.List;
import javax.annotation.Resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.module.channel.enums.ErrorCodeConstants.ADDRESS_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link ChannelAddressServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ChannelAddressServiceImpl.class)
public class ChannelAddressServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ChannelAddressServiceImpl customerService;

    @Resource
    private ChannelAddressMapper channelAddressMapper;

    @Test
    public void testCreateCustomer_success() {
        // 准备参数
        AddressCreateReqVO reqVO = randomPojo(AddressCreateReqVO.class);

        // 调用
        Integer customerId = customerService.createAddress(reqVO);
        // 断言
        assertNotNull(customerId);
        // 校验记录的属性是否正确
        ChannelAddressDO customer = channelAddressMapper.selectById(customerId);
        assertPojoEquals(reqVO, customer);
    }

    @Test
    public void testUpdateCustomer_success() {
        // mock 数据
        ChannelAddressDO dbCustomer = randomPojo(ChannelAddressDO.class);
        channelAddressMapper.insert(dbCustomer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AddressUpdateReqVO reqVO = randomPojo(AddressUpdateReqVO.class, o -> {
            o.setId(dbCustomer.getId()); // 设置更新的 ID
        });

        // 调用
        customerService.updateAddress(reqVO);
        // 校验是否更新正确
        ChannelAddressDO customer = channelAddressMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, customer);
    }

    @Test
    public void testUpdateCustomer_notExists() {
        // 准备参数
        AddressUpdateReqVO reqVO = randomPojo(AddressUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> customerService.updateAddress(reqVO), ADDRESS_NOT_EXISTS);
    }

    @Test
    public void testDeleteCustomer_success() {
        // mock 数据
        ChannelAddressDO dbCustomer = randomPojo(ChannelAddressDO.class);
        channelAddressMapper.insert(dbCustomer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbCustomer.getId();

        // 调用
        customerService.deleteAddress(id);
       // 校验数据不存在了
       assertNull(channelAddressMapper.selectById(id));
    }

    @Test
    public void testDeleteCustomer_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = RandomUtils.randomInteger();
        // 调用, 并断言异常
        assertServiceException(() -> customerService.deleteAddress(id), ADDRESS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCustomerPage() {
       // mock 数据
       ChannelAddressDO dbCustomer = randomPojo(ChannelAddressDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setPosCode(null);
           o.setMobile(null);
           o.setRefType(null);
           o.setReceiverCountry(null);
           o.setReceiverState(null);
           o.setReceiverCity(null);
           o.setReceiverDistrict(null);
           o.setReceiverTown(null);
           o.setReceiverId(null);
           o.setOpenBuyerNick(null);
           o.setOpenBuyerId(null);
           o.setCreateTime(null);
           o.setRemark(null);
           o.setAddressId(null);
           o.setUserId(null);
       });
       channelAddressMapper.insert(dbCustomer);
       // 测试 refOid 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRefOid(null)));
       // 测试 posCode 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setPosCode(null)));
       // 测试 mobile 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setMobile(null)));
       // 测试 refType 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRefType(null)));
       // 测试 receiverCountry 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverCountry(null)));
       // 测试 receiverState 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverState(null)));
       // 测试 receiverCity 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverCity(null)));
       // 测试 receiverDistrict 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverDistrict(null)));
       // 测试 receiverTown 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverTown(null)));
       // 测试 receiverId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverId(null)));
       // 测试 openBuyerNick 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setOpenBuyerNick(null)));
       // 测试 openBuyerId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setOpenBuyerId(null)));
       // 测试 createTime 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCreateTime(null)));
       // 测试 remark 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRemark(null)));
       // 测试 addressId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddressId(null)));
       // 测试 userId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setUserId(null)));
       // 准备参数
       AddressPageReqVO reqVO = new AddressPageReqVO();
       reqVO.setRefOid(null);
       reqVO.setPosCode(null);
       reqVO.setMobile(null);
       reqVO.setRefType(null);
       reqVO.setReceiverCountry(null);
       reqVO.setReceiverState(null);
       reqVO.setReceiverCity(null);
       reqVO.setReceiverDistrict(null);
       reqVO.setReceiverTown(null);
       reqVO.setReceiverId(null);
       reqVO.setOpenBuyerNick(null);
       reqVO.setOpenBuyerId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAddressId(null);
       reqVO.setUserId(null);

       // 调用
       PageResult<ChannelAddressDO> pageResult = customerService.getAddressPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCustomer, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCustomerList() {
       // mock 数据
       ChannelAddressDO dbCustomer = randomPojo(ChannelAddressDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setPosCode(null);
           o.setMobile(null);
           o.setRefType(null);
           o.setReceiverCountry(null);
           o.setReceiverState(null);
           o.setReceiverCity(null);
           o.setReceiverDistrict(null);
           o.setReceiverTown(null);
           o.setReceiverId(null);
           o.setOpenBuyerNick(null);
           o.setOpenBuyerId(null);
           o.setCreateTime(null);
           o.setRemark(null);
           o.setAddressId(null);
           o.setUserId(null);
       });
       channelAddressMapper.insert(dbCustomer);
       // 测试 refOid 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRefOid(null)));
       // 测试 posCode 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setPosCode(null)));
       // 测试 mobile 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setMobile(null)));
       // 测试 refType 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRefType(null)));
       // 测试 receiverCountry 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverCountry(null)));
       // 测试 receiverState 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverState(null)));
       // 测试 receiverCity 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverCity(null)));
       // 测试 receiverDistrict 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverDistrict(null)));
       // 测试 receiverTown 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverTown(null)));
       // 测试 receiverId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setReceiverId(null)));
       // 测试 openBuyerNick 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setOpenBuyerNick(null)));
       // 测试 openBuyerId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setOpenBuyerId(null)));
       // 测试 createTime 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCreateTime(null)));
       // 测试 remark 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setRemark(null)));
       // 测试 addressId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddressId(null)));
       // 测试 userId 不匹配
       channelAddressMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setUserId(null)));
       // 准备参数
       AddressExportReqVO reqVO = new AddressExportReqVO();
       reqVO.setRefOid(null);
       reqVO.setPosCode(null);
       reqVO.setMobile(null);
       reqVO.setRefType(null);
       reqVO.setReceiverCountry(null);
       reqVO.setReceiverState(null);
       reqVO.setReceiverCity(null);
       reqVO.setReceiverDistrict(null);
       reqVO.setReceiverTown(null);
       reqVO.setReceiverId(null);
       reqVO.setOpenBuyerNick(null);
       reqVO.setOpenBuyerId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAddressId(null);
       reqVO.setUserId(null);

       // 调用
       List<ChannelAddressDO> list = customerService.getAddressList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCustomer, list.get(0));
    }

}
