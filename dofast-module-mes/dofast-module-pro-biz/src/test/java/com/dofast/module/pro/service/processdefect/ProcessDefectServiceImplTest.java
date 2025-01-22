package com.dofast.module.pro.service.processdefect;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.processdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.processdefect.ProcessDefectDO;
import com.dofast.module.pro.dal.mysql.processdefect.ProcessDefectMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ProcessDefectServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ProcessDefectServiceImpl.class)
public class ProcessDefectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProcessDefectServiceImpl cessDefectService;

    @Resource
    private ProcessDefectMapper cessDefectMapper;

    @Test
    public void testCreatecessDefect_success() {
        // 准备参数
        ProcessDefectCreateReqVO reqVO = randomPojo(ProcessDefectCreateReqVO.class);

        // 调用
        Long cessDefectId = cessDefectService.createcessDefect(reqVO);
        // 断言
        assertNotNull(cessDefectId);
        // 校验记录的属性是否正确
        ProcessDefectDO cessDefect = cessDefectMapper.selectById(cessDefectId);
        assertPojoEquals(reqVO, cessDefect);
    }

    @Test
    public void testUpdatecessDefect_success() {
        // mock 数据
        ProcessDefectDO dbcessDefect = randomPojo(ProcessDefectDO.class);
        cessDefectMapper.insert(dbcessDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProcessDefectUpdateReqVO reqVO = randomPojo(ProcessDefectUpdateReqVO.class, o -> {
            o.setId(dbcessDefect.getId()); // 设置更新的 ID
        });

        // 调用
        cessDefectService.updatecessDefect(reqVO);
        // 校验是否更新正确
        ProcessDefectDO cessDefect = cessDefectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cessDefect);
    }

    @Test
    public void testUpdatecessDefect_notExists() {
        // 准备参数
        ProcessDefectUpdateReqVO reqVO = randomPojo(ProcessDefectUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cessDefectService.updatecessDefect(reqVO), CESS_DEFECT_NOT_EXISTS);
    }

    @Test
    public void testDeletecessDefect_success() {
        // mock 数据
        ProcessDefectDO dbcessDefect = randomPojo(ProcessDefectDO.class);
        cessDefectMapper.insert(dbcessDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbcessDefect.getId();

        // 调用
        cessDefectService.deletecessDefect(id);
       // 校验数据不存在了
       assertNull(cessDefectMapper.selectById(id));
    }

    @Test
    public void testDeletecessDefect_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cessDefectService.deletecessDefect(id), CESS_DEFECT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessDefectPage() {
       // mock 数据
       ProcessDefectDO dbcessDefect = randomPojo(ProcessDefectDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setProcessCode(null);
           o.setDefectName(null);
       });
       cessDefectMapper.insert(dbcessDefect);
       // 测试 createTime 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setCreateTime(null)));
       // 测试 processCode 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setProcessCode(null)));
       // 测试 defectName 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setDefectName(null)));
       // 准备参数
       ProcessDefectPageReqVO reqVO = new ProcessDefectPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setProcessCode(null);
       reqVO.setDefectName(null);

       // 调用
       PageResult<ProcessDefectDO> pageResult = cessDefectService.getcessDefectPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbcessDefect, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessDefectList() {
       // mock 数据
       ProcessDefectDO dbcessDefect = randomPojo(ProcessDefectDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setProcessCode(null);
           o.setDefectName(null);
       });
       cessDefectMapper.insert(dbcessDefect);
       // 测试 createTime 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setCreateTime(null)));
       // 测试 processCode 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setProcessCode(null)));
       // 测试 defectName 不匹配
       cessDefectMapper.insert(cloneIgnoreId(dbcessDefect, o -> o.setDefectName(null)));
       // 准备参数
       ProcessDefectExportReqVO reqVO = new ProcessDefectExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setProcessCode(null);
       reqVO.setDefectName(null);

       // 调用
       List<ProcessDefectDO> list = cessDefectService.getcessDefectList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbcessDefect, list.get(0));
    }

}
