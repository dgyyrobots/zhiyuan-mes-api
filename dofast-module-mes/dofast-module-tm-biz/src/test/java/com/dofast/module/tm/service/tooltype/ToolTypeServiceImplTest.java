package com.dofast.module.tm.service.tooltype;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.tm.controller.admin.tooltype.vo.*;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import com.dofast.module.tm.dal.mysql.tooltype.ToolTypeMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.tm.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ToolTypeServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ToolTypeServiceImpl.class)
public class ToolTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ToolTypeServiceImpl toolTypeService;

    @Resource
    private ToolTypeMapper toolTypeMapper;

    @Test
    public void testCreateToolType_success() {
        // 准备参数
        ToolTypeCreateReqVO reqVO = randomPojo(ToolTypeCreateReqVO.class);

        // 调用
        Long toolTypeId = toolTypeService.createToolType(reqVO);
        // 断言
        assertNotNull(toolTypeId);
        // 校验记录的属性是否正确
        ToolTypeDO toolType = toolTypeMapper.selectById(toolTypeId);
        assertPojoEquals(reqVO, toolType);
    }

    @Test
    public void testUpdateToolType_success() {
        // mock 数据
        ToolTypeDO dbToolType = randomPojo(ToolTypeDO.class);
        toolTypeMapper.insert(dbToolType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ToolTypeUpdateReqVO reqVO = randomPojo(ToolTypeUpdateReqVO.class, o -> {
            o.setId(dbToolType.getId()); // 设置更新的 ID
        });

        // 调用
        toolTypeService.updateToolType(reqVO);
        // 校验是否更新正确
        ToolTypeDO toolType = toolTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, toolType);
    }

    @Test
    public void testUpdateToolType_notExists() {
        // 准备参数
        ToolTypeUpdateReqVO reqVO = randomPojo(ToolTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> toolTypeService.updateToolType(reqVO), TOOL_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteToolType_success() {
        // mock 数据
        ToolTypeDO dbToolType = randomPojo(ToolTypeDO.class);
        toolTypeMapper.insert(dbToolType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbToolType.getId();

        // 调用
        toolTypeService.deleteToolType(id);
       // 校验数据不存在了
       assertNull(toolTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteToolType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> toolTypeService.deleteToolType(id), TOOL_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetToolTypePage() {
       // mock 数据
       ToolTypeDO dbToolType = randomPojo(ToolTypeDO.class, o -> { // 等会查询到
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setCodeFlag(null);
           o.setMaintenType(null);
           o.setMaintenPeriod(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       toolTypeMapper.insert(dbToolType);
       // 测试 toolTypeCode 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setToolTypeName(null)));
       // 测试 codeFlag 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setCodeFlag(null)));
       // 测试 maintenType 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setMaintenType(null)));
       // 测试 maintenPeriod 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setMaintenPeriod(null)));
       // 测试 remark 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setCreateTime(null)));
       // 准备参数
       ToolTypePageReqVO reqVO = new ToolTypePageReqVO();
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setCodeFlag(null);
       reqVO.setMaintenType(null);
       reqVO.setMaintenPeriod(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ToolTypeDO> pageResult = toolTypeService.getToolTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbToolType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetToolTypeList() {
       // mock 数据
       ToolTypeDO dbToolType = randomPojo(ToolTypeDO.class, o -> { // 等会查询到
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setCodeFlag(null);
           o.setMaintenType(null);
           o.setMaintenPeriod(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       toolTypeMapper.insert(dbToolType);
       // 测试 toolTypeCode 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setToolTypeName(null)));
       // 测试 codeFlag 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setCodeFlag(null)));
       // 测试 maintenType 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setMaintenType(null)));
       // 测试 maintenPeriod 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setMaintenPeriod(null)));
       // 测试 remark 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       toolTypeMapper.insert(cloneIgnoreId(dbToolType, o -> o.setCreateTime(null)));
       // 准备参数
       ToolTypeExportReqVO reqVO = new ToolTypeExportReqVO();
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setCodeFlag(null);
       reqVO.setMaintenType(null);
       reqVO.setMaintenPeriod(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ToolTypeDO> list = toolTypeService.getToolTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbToolType, list.get(0));
    }

}
