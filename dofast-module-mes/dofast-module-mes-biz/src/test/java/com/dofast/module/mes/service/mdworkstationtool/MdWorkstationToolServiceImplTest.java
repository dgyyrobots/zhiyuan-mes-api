package com.dofast.module.mes.service.mdworkstationtool;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdworkstationtool.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationtool.MdWorkstationToolDO;
import com.dofast.module.mes.dal.mysql.mdworkstationtool.MdWorkstationToolMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link MdWorkstationToolServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MdWorkstationToolServiceImpl.class)
public class MdWorkstationToolServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdWorkstationToolServiceImpl mdWorkstationToolService;

    @Resource
    private MdWorkstationToolMapper mdWorkstationToolMapper;

    @Test
    public void testCreateMdWorkstationTool_success() {
        // 准备参数
        MdWorkstationToolCreateReqVO reqVO = randomPojo(MdWorkstationToolCreateReqVO.class);

        // 调用
        Long mdWorkstationToolId = mdWorkstationToolService.createMdWorkstationTool(reqVO);
        // 断言
        assertNotNull(mdWorkstationToolId);
        // 校验记录的属性是否正确
        MdWorkstationToolDO mdWorkstationTool = mdWorkstationToolMapper.selectById(mdWorkstationToolId);
        assertPojoEquals(reqVO, mdWorkstationTool);
    }

    @Test
    public void testUpdateMdWorkstationTool_success() {
        // mock 数据
        MdWorkstationToolDO dbMdWorkstationTool = randomPojo(MdWorkstationToolDO.class);
        mdWorkstationToolMapper.insert(dbMdWorkstationTool);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdWorkstationToolUpdateReqVO reqVO = randomPojo(MdWorkstationToolUpdateReqVO.class, o -> {
            o.setId(dbMdWorkstationTool.getId()); // 设置更新的 ID
        });

        // 调用
        mdWorkstationToolService.updateMdWorkstationTool(reqVO);
        // 校验是否更新正确
        MdWorkstationToolDO mdWorkstationTool = mdWorkstationToolMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdWorkstationTool);
    }

    @Test
    public void testUpdateMdWorkstationTool_notExists() {
        // 准备参数
        MdWorkstationToolUpdateReqVO reqVO = randomPojo(MdWorkstationToolUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationToolService.updateMdWorkstationTool(reqVO), MD_WORKSTATION_TOOL_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdWorkstationTool_success() {
        // mock 数据
        MdWorkstationToolDO dbMdWorkstationTool = randomPojo(MdWorkstationToolDO.class);
        mdWorkstationToolMapper.insert(dbMdWorkstationTool);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdWorkstationTool.getId();

        // 调用
        mdWorkstationToolService.deleteMdWorkstationTool(id);
       // 校验数据不存在了
       assertNull(mdWorkstationToolMapper.selectById(id));
    }

    @Test
    public void testDeleteMdWorkstationTool_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationToolService.deleteMdWorkstationTool(id), MD_WORKSTATION_TOOL_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationToolPage() {
       // mock 数据
       MdWorkstationToolDO dbMdWorkstationTool = randomPojo(MdWorkstationToolDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setToolTypeId(null);
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationToolMapper.insert(dbMdWorkstationTool);
       // 测试 workstationId 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setWorkstationId(null)));
       // 测试 toolTypeId 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeId(null)));
       // 测试 toolTypeCode 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeName(null)));
       // 测试 quantity 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationToolPageReqVO reqVO = new MdWorkstationToolPageReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setToolTypeId(null);
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdWorkstationToolDO> pageResult = mdWorkstationToolService.getMdWorkstationToolPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdWorkstationTool, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationToolList() {
       // mock 数据
       MdWorkstationToolDO dbMdWorkstationTool = randomPojo(MdWorkstationToolDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setToolTypeId(null);
           o.setToolTypeCode(null);
           o.setToolTypeName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationToolMapper.insert(dbMdWorkstationTool);
       // 测试 workstationId 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setWorkstationId(null)));
       // 测试 toolTypeId 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeId(null)));
       // 测试 toolTypeCode 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeCode(null)));
       // 测试 toolTypeName 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setToolTypeName(null)));
       // 测试 quantity 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationToolMapper.insert(cloneIgnoreId(dbMdWorkstationTool, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationToolExportReqVO reqVO = new MdWorkstationToolExportReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setToolTypeId(null);
       reqVO.setToolTypeCode(null);
       reqVO.setToolTypeName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdWorkstationToolDO> list = mdWorkstationToolService.getMdWorkstationToolList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdWorkstationTool, list.get(0));
    }

}
