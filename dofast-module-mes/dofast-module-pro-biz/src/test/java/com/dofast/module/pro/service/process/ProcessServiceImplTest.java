package com.dofast.module.pro.service.process;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.process.vo.*;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.module.pro.dal.mysql.process.ProcessMapper;
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
 * {@link ProcessServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ProcessServiceImpl.class)
public class ProcessServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProcessServiceImpl cessService;

    @Resource
    private ProcessMapper cessMapper;

    @Test
    public void testCreatecess_success() {
        // 准备参数
        ProcessCreateReqVO reqVO = randomPojo(ProcessCreateReqVO.class);

        // 调用
        Long cessId = cessService.createcess(reqVO);
        // 断言
        assertNotNull(cessId);
        // 校验记录的属性是否正确
        ProcessDO cess = cessMapper.selectById(cessId);
        assertPojoEquals(reqVO, cess);
    }

    @Test
    public void testUpdatecess_success() {
        // mock 数据
        ProcessDO dbcess = randomPojo(ProcessDO.class);
        cessMapper.insert(dbcess);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProcessUpdateReqVO reqVO = randomPojo(ProcessUpdateReqVO.class, o -> {
            o.setId(dbcess.getId()); // 设置更新的 ID
        });

        // 调用
        cessService.updatecess(reqVO);
        // 校验是否更新正确
        ProcessDO cess = cessMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cess);
    }

    @Test
    public void testUpdatecess_notExists() {
        // 准备参数
        ProcessUpdateReqVO reqVO = randomPojo(ProcessUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cessService.updatecess(reqVO), CESS_NOT_EXISTS);
    }

    @Test
    public void testDeletecess_success() {
        // mock 数据
        ProcessDO dbcess = randomPojo(ProcessDO.class);
        cessMapper.insert(dbcess);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbcess.getId();

        // 调用
        cessService.deletecess(id);
       // 校验数据不存在了
       assertNull(cessMapper.selectById(id));
    }

    @Test
    public void testDeletecess_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cessService.deletecess(id), CESS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessPage() {
       // mock 数据
       ProcessDO dbcess = randomPojo(ProcessDO.class, o -> { // 等会查询到
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setAttention(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       cessMapper.insert(dbcess);
       // 测试 processCode 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setProcessName(null)));
       // 测试 attention 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttention(null)));
       // 测试 enableFlag 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setCreateTime(null)));
       // 准备参数
       ProcessPageReqVO reqVO = new ProcessPageReqVO();
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setAttention(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProcessDO> pageResult = cessService.getcessPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbcess, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessList() {
       // mock 数据
       ProcessDO dbcess = randomPojo(ProcessDO.class, o -> { // 等会查询到
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setAttention(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       cessMapper.insert(dbcess);
       // 测试 processCode 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setProcessName(null)));
       // 测试 attention 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttention(null)));
       // 测试 enableFlag 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       cessMapper.insert(cloneIgnoreId(dbcess, o -> o.setCreateTime(null)));
       // 准备参数
       ProcessExportReqVO reqVO = new ProcessExportReqVO();
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setAttention(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ProcessDO> list = cessService.getcessList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbcess, list.get(0));
    }

}
