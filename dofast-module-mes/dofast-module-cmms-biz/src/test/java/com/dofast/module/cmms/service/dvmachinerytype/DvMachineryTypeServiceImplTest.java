package com.dofast.module.cmms.service.dvmachinerytype;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvmachinerytype.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinerytype.DvMachineryTypeDO;
import com.dofast.module.cmms.dal.mysql.dvmachinerytype.DvMachineryTypeMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DvMachineryTypeServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvMachineryTypeServiceImpl.class)
public class DvMachineryTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvMachineryTypeServiceImpl dvMachineryTypeService;

    @Resource
    private DvMachineryTypeMapper dvMachineryTypeMapper;

    @Test
    public void testCreateDvMachineryType_success() {
        // 准备参数
        DvMachineryTypeCreateReqVO reqVO = randomPojo(DvMachineryTypeCreateReqVO.class);

        // 调用
        Long dvMachineryTypeId = dvMachineryTypeService.createDvMachineryType(reqVO);
        // 断言
        assertNotNull(dvMachineryTypeId);
        // 校验记录的属性是否正确
        DvMachineryTypeDO dvMachineryType = dvMachineryTypeMapper.selectById(dvMachineryTypeId);
        assertPojoEquals(reqVO, dvMachineryType);
    }

    @Test
    public void testUpdateDvMachineryType_success() {
        // mock 数据
        DvMachineryTypeDO dbDvMachineryType = randomPojo(DvMachineryTypeDO.class);
        dvMachineryTypeMapper.insert(dbDvMachineryType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvMachineryTypeUpdateReqVO reqVO = randomPojo(DvMachineryTypeUpdateReqVO.class, o -> {
            o.setMachineryTypeId(dbDvMachineryType.getId()); // 设置更新的 ID
        });

        // 调用
        dvMachineryTypeService.updateDvMachineryType(reqVO);
        // 校验是否更新正确
        DvMachineryTypeDO dvMachineryType = dvMachineryTypeMapper.selectById(reqVO.getMachineryTypeId()); // 获取最新的
        assertPojoEquals(reqVO, dvMachineryType);
    }

    @Test
    public void testUpdateDvMachineryType_notExists() {
        // 准备参数
        DvMachineryTypeUpdateReqVO reqVO = randomPojo(DvMachineryTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvMachineryTypeService.updateDvMachineryType(reqVO), DV_MACHINERY_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvMachineryType_success() {
        // mock 数据
        DvMachineryTypeDO dbDvMachineryType = randomPojo(DvMachineryTypeDO.class);
        dvMachineryTypeMapper.insert(dbDvMachineryType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvMachineryType.getId();

        // 调用
        dvMachineryTypeService.deleteDvMachineryType(id);
       // 校验数据不存在了
       assertNull(dvMachineryTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteDvMachineryType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvMachineryTypeService.deleteDvMachineryType(id), DV_MACHINERY_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvMachineryTypePage() {
       // mock 数据
       DvMachineryTypeDO dbDvMachineryType = randomPojo(DvMachineryTypeDO.class, o -> { // 等会查询到
           o.setMachineryTypeCode(null);
           o.setMachineryTypeName(null);
           o.setParentTypeId(null);
           o.setAncestors(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvMachineryTypeMapper.insert(dbDvMachineryType);
       // 测试 machineryTypeCode 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setMachineryTypeCode(null)));
       // 测试 machineryTypeName 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setMachineryTypeName(null)));
       // 测试 parentTypeId 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setParentTypeId(null)));
       // 测试 ancestors 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAncestors(null)));
       // 测试 enableFlag 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setCreateTime(null)));
       // 准备参数
       DvMachineryTypePageReqVO reqVO = new DvMachineryTypePageReqVO();
       reqVO.setMachineryTypeCode(null);
       reqVO.setMachineryTypeName(null);
       reqVO.setParentTypeId(null);
       reqVO.setAncestors(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvMachineryTypeDO> pageResult = dvMachineryTypeService.getDvMachineryTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvMachineryType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvMachineryTypeList() {
       // mock 数据
       DvMachineryTypeDO dbDvMachineryType = randomPojo(DvMachineryTypeDO.class, o -> { // 等会查询到
           o.setMachineryTypeCode(null);
           o.setMachineryTypeName(null);
           o.setParentTypeId(null);
           o.setAncestors(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvMachineryTypeMapper.insert(dbDvMachineryType);
       // 测试 machineryTypeCode 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setMachineryTypeCode(null)));
       // 测试 machineryTypeName 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setMachineryTypeName(null)));
       // 测试 parentTypeId 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setParentTypeId(null)));
       // 测试 ancestors 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAncestors(null)));
       // 测试 enableFlag 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvMachineryTypeMapper.insert(cloneIgnoreId(dbDvMachineryType, o -> o.setCreateTime(null)));
       // 准备参数
       DvMachineryTypeExportReqVO reqVO = new DvMachineryTypeExportReqVO();
       reqVO.setMachineryTypeCode(null);
       reqVO.setMachineryTypeName(null);
       reqVO.setParentTypeId(null);
       reqVO.setAncestors(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvMachineryTypeDO> list = dvMachineryTypeService.getDvMachineryTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvMachineryType, list.get(0));
    }

}
