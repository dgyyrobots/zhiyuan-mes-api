package com.dofast.module.tm.service.tool;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.tm.controller.admin.tool.vo.*;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;
import com.dofast.module.tm.dal.mysql.tool.ToolMapper;
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
 * {@link ToolServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ToolServiceImpl.class)
public class ToolServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ToolServiceImpl toolService;

    @Resource
    private ToolMapper toolMapper;

    @Test
    public void testCreateTool_success() {
        // 准备参数
        ToolCreateReqVO reqVO = randomPojo(ToolCreateReqVO.class);

        // 调用
        Long toolId = toolService.createTool(reqVO);
        // 断言
        assertNotNull(toolId);
        // 校验记录的属性是否正确
        ToolDO tool = toolMapper.selectById(toolId);
        assertPojoEquals(reqVO, tool);
    }

    @Test
    public void testUpdateTool_success() {
        // mock 数据
        ToolDO dbTool = randomPojo(ToolDO.class);
        toolMapper.insert(dbTool);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ToolUpdateReqVO reqVO = randomPojo(ToolUpdateReqVO.class, o -> {
            o.setId(dbTool.getId()); // 设置更新的 ID
        });

        // 调用
        toolService.updateTool(reqVO);
        // 校验是否更新正确
        ToolDO tool = toolMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, tool);
    }

    @Test
    public void testUpdateTool_notExists() {
        // 准备参数
        ToolUpdateReqVO reqVO = randomPojo(ToolUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> toolService.updateTool(reqVO), TOOL_NOT_EXISTS);
    }

    @Test
    public void testDeleteTool_success() {
        // mock 数据
        ToolDO dbTool = randomPojo(ToolDO.class);
        toolMapper.insert(dbTool);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTool.getId();

        // 调用
        toolService.deleteTool(id);
       // 校验数据不存在了
       assertNull(toolMapper.selectById(id));
    }

    @Test
    public void testDeleteTool_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> toolService.deleteTool(id), TOOL_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetToolPage() {
       // mock 数据
       ToolDO dbTool = randomPojo(ToolDO.class, o -> { // 等会查询到
           o.setToolCode(null);
           o.setToolName(null);
           o.setBrand(null);
           o.setSpec(null);
           o.setToolTypeId(null);
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setCodeFlag(null);
           o.setQuantity(null);
           o.setQuantityAvail(null);
           o.setMaintenType(null);
           o.setNextMaintenPeriod(null);
           o.setNextMaintenDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       toolMapper.insert(dbTool);
       // 测试 toolCode 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolCode(null)));
       // 测试 toolName 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolName(null)));
       // 测试 brand 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setBrand(null)));
       // 测试 spec 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setSpec(null)));
       // 测试 toolTypeId 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeId(null)));
       // 测试 toolTypeCode 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeName(null)));
       // 测试 codeFlag 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setCodeFlag(null)));
       // 测试 quantity 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setQuantity(null)));
       // 测试 quantityAvail 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setQuantityAvail(null)));
       // 测试 maintenType 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setMaintenType(null)));
       // 测试 nextMaintenPeriod 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setNextMaintenPeriod(null)));
       // 测试 nextMaintenDate 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setNextMaintenDate(null)));
       // 测试 status 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setCreateTime(null)));
       // 准备参数
       ToolPageReqVO reqVO = new ToolPageReqVO();
       reqVO.setToolCode(null);
       reqVO.setToolName(null);
       reqVO.setBrand(null);
       reqVO.setSpec(null);
       reqVO.setToolTypeId(null);
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setCodeFlag(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityAvail(null);
       reqVO.setMaintenType(null);
       reqVO.setNextMaintenPeriod(null);
       reqVO.setNextMaintenDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ToolDO> pageResult = toolService.getToolPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTool, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetToolList() {
       // mock 数据
       ToolDO dbTool = randomPojo(ToolDO.class, o -> { // 等会查询到
           o.setToolCode(null);
           o.setToolName(null);
           o.setBrand(null);
           o.setSpec(null);
           o.setToolTypeId(null);
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setCodeFlag(null);
           o.setQuantity(null);
           o.setQuantityAvail(null);
           o.setMaintenType(null);
           o.setNextMaintenPeriod(null);
           o.setNextMaintenDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       toolMapper.insert(dbTool);
       // 测试 toolCode 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolCode(null)));
       // 测试 toolName 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolName(null)));
       // 测试 brand 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setBrand(null)));
       // 测试 spec 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setSpec(null)));
       // 测试 toolTypeId 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeId(null)));
       // 测试 toolTypeCode 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setToolTypeName(null)));
       // 测试 codeFlag 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setCodeFlag(null)));
       // 测试 quantity 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setQuantity(null)));
       // 测试 quantityAvail 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setQuantityAvail(null)));
       // 测试 maintenType 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setMaintenType(null)));
       // 测试 nextMaintenPeriod 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setNextMaintenPeriod(null)));
       // 测试 nextMaintenDate 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setNextMaintenDate(null)));
       // 测试 status 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       toolMapper.insert(cloneIgnoreId(dbTool, o -> o.setCreateTime(null)));
       // 准备参数
       ToolExportReqVO reqVO = new ToolExportReqVO();
       reqVO.setToolCode(null);
       reqVO.setToolName(null);
       reqVO.setBrand(null);
       reqVO.setSpec(null);
       reqVO.setToolTypeId(null);
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setCodeFlag(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityAvail(null);
       reqVO.setMaintenType(null);
       reqVO.setNextMaintenPeriod(null);
       reqVO.setNextMaintenDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ToolDO> list = toolService.getToolList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTool, list.get(0));
    }

}
