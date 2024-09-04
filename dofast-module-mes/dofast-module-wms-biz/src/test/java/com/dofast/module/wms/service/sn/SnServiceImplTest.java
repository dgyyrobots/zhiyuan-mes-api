package com.dofast.module.wms.service.sn;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.sn.vo.*;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;
import com.dofast.module.wms.dal.mysql.sn.SnMapper;
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
 * {@link SnServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(SnServiceImpl.class)
public class SnServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SnServiceImpl snService;

    @Resource
    private SnMapper snMapper;

    @Test
    public void testCreateSn_success() {
        // 准备参数
        SnCreateReqVO reqVO = randomPojo(SnCreateReqVO.class);

        // 调用
        Long snId = snService.createSn(reqVO);
        // 断言
        assertNotNull(snId);
        // 校验记录的属性是否正确
        SnDO sn = snMapper.selectById(snId);
        assertPojoEquals(reqVO, sn);
    }

    @Test
    public void testUpdateSn_success() {
        // mock 数据
        SnDO dbSn = randomPojo(SnDO.class);
        snMapper.insert(dbSn);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SnUpdateReqVO reqVO = randomPojo(SnUpdateReqVO.class, o -> {
            o.setId(dbSn.getId()); // 设置更新的 ID
        });

        // 调用
        snService.updateSn(reqVO);
        // 校验是否更新正确
        SnDO sn = snMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, sn);
    }

    @Test
    public void testUpdateSn_notExists() {
        // 准备参数
        SnUpdateReqVO reqVO = randomPojo(SnUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> snService.updateSn(reqVO), SN_NOT_EXISTS);
    }

    @Test
    public void testDeleteSn_success() {
        // mock 数据
        SnDO dbSn = randomPojo(SnDO.class);
        snMapper.insert(dbSn);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSn.getId();

        // 调用
        snService.deleteSn(id);
       // 校验数据不存在了
       assertNull(snMapper.selectById(id));
    }

    @Test
    public void testDeleteSn_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> snService.deleteSn(id), SN_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSnPage() {
       // mock 数据
       SnDO dbSn = randomPojo(SnDO.class, o -> { // 等会查询到
           o.setSnCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBatchCode(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       snMapper.insert(dbSn);
       // 测试 snCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setSnCode(null)));
       // 测试 itemId 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setBatchCode(null)));
       // 测试 remark 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setCreateTime(null)));
       // 准备参数
       SnPageReqVO reqVO = new SnPageReqVO();
       reqVO.setSnCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBatchCode(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SnDO> pageResult = snService.getSnPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSn, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSnList() {
       // mock 数据
       SnDO dbSn = randomPojo(SnDO.class, o -> { // 等会查询到
           o.setSnCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBatchCode(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       snMapper.insert(dbSn);
       // 测试 snCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setSnCode(null)));
       // 测试 itemId 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setBatchCode(null)));
       // 测试 remark 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       snMapper.insert(cloneIgnoreId(dbSn, o -> o.setCreateTime(null)));
       // 准备参数
       SnExportReqVO reqVO = new SnExportReqVO();
       reqVO.setSnCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBatchCode(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SnDO> list = snService.getSnList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSn, list.get(0));
    }

}
