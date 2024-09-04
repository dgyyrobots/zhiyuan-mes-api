package com.dofast.module.wms.service.rtvendor;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.rtvendor.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;
import com.dofast.module.wms.dal.mysql.rtvendor.RtVendorMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link RtVendorServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RtVendorServiceImpl.class)
public class RtVendorServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RtVendorServiceImpl rtVendorService;

    @Resource
    private RtVendorMapper rtVendorMapper;

    @Test
    public void testCreateRtVendor_success() {
        // 准备参数
        RtVendorCreateReqVO reqVO = randomPojo(RtVendorCreateReqVO.class);

        // 调用
        Long rtVendorId = rtVendorService.createRtVendor(reqVO);
        // 断言
        assertNotNull(rtVendorId);
        // 校验记录的属性是否正确
        RtVendorDO rtVendor = rtVendorMapper.selectById(rtVendorId);
        assertPojoEquals(reqVO, rtVendor);
    }

    @Test
    public void testUpdateRtVendor_success() {
        // mock 数据
        RtVendorDO dbRtVendor = randomPojo(RtVendorDO.class);
        rtVendorMapper.insert(dbRtVendor);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RtVendorUpdateReqVO reqVO = randomPojo(RtVendorUpdateReqVO.class, o -> {
            o.setId(dbRtVendor.getId()); // 设置更新的 ID
        });

        // 调用
        rtVendorService.updateRtVendor(reqVO);
        // 校验是否更新正确
        RtVendorDO rtVendor = rtVendorMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rtVendor);
    }

    @Test
    public void testUpdateRtVendor_notExists() {
        // 准备参数
        RtVendorUpdateReqVO reqVO = randomPojo(RtVendorUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rtVendorService.updateRtVendor(reqVO), RT_VENDOR_NOT_EXISTS);
    }

    @Test
    public void testDeleteRtVendor_success() {
        // mock 数据
        RtVendorDO dbRtVendor = randomPojo(RtVendorDO.class);
        rtVendorMapper.insert(dbRtVendor);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRtVendor.getId();

        // 调用
        rtVendorService.deleteRtVendor(id);
       // 校验数据不存在了
       assertNull(rtVendorMapper.selectById(id));
    }

    @Test
    public void testDeleteRtVendor_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rtVendorService.deleteRtVendor(id), RT_VENDOR_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtVendorPage() {
       // mock 数据
       RtVendorDO dbRtVendor = randomPojo(RtVendorDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setPoCode(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setBatchCode(null);
           o.setRtDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       rtVendorMapper.insert(dbRtVendor);
       // 测试 rtCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtName(null)));
       // 测试 poCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setPoCode(null)));
       // 测试 vendorId 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorNick(null)));
       // 测试 batchCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setBatchCode(null)));
       // 测试 rtDate 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtDate(null)));
       // 测试 status 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setCreateTime(null)));
       // 准备参数
       RtVendorPageReqVO reqVO = new RtVendorPageReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setPoCode(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setBatchCode(null);
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RtVendorDO> pageResult = rtVendorService.getRtVendorPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRtVendor, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtVendorList() {
       // mock 数据
       RtVendorDO dbRtVendor = randomPojo(RtVendorDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setPoCode(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setBatchCode(null);
           o.setRtDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       rtVendorMapper.insert(dbRtVendor);
       // 测试 rtCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtName(null)));
       // 测试 poCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setPoCode(null)));
       // 测试 vendorId 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setVendorNick(null)));
       // 测试 batchCode 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setBatchCode(null)));
       // 测试 rtDate 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRtDate(null)));
       // 测试 status 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtVendorMapper.insert(cloneIgnoreId(dbRtVendor, o -> o.setCreateTime(null)));
       // 准备参数
       RtVendorExportReqVO reqVO = new RtVendorExportReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setPoCode(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setBatchCode(null);
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RtVendorDO> list = rtVendorService.getRtVendorList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRtVendor, list.get(0));
    }

}
