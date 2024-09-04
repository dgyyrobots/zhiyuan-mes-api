package com.dofast.module.mes.service.mdworkstationworker;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdworkstationworker.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.module.mes.dal.mysql.mdworkstationworker.MdWorkstationWorkerMapper;
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
 * {@link MdWorkstationWorkerServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MdWorkstationWorkerServiceImpl.class)
public class MdWorkstationWorkerServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdWorkstationWorkerServiceImpl mdWorkstationWorkerService;

    @Resource
    private MdWorkstationWorkerMapper mdWorkstationWorkerMapper;

    @Test
    public void testCreateMdWorkstationWorker_success() {
        // 准备参数
        MdWorkstationWorkerCreateReqVO reqVO = randomPojo(MdWorkstationWorkerCreateReqVO.class);

        // 调用
        Long mdWorkstationWorkerId = mdWorkstationWorkerService.createMdWorkstationWorker(reqVO);
        // 断言
        assertNotNull(mdWorkstationWorkerId);
        // 校验记录的属性是否正确
        MdWorkstationWorkerDO mdWorkstationWorker = mdWorkstationWorkerMapper.selectById(mdWorkstationWorkerId);
        assertPojoEquals(reqVO, mdWorkstationWorker);
    }

    @Test
    public void testUpdateMdWorkstationWorker_success() {
        // mock 数据
        MdWorkstationWorkerDO dbMdWorkstationWorker = randomPojo(MdWorkstationWorkerDO.class);
        mdWorkstationWorkerMapper.insert(dbMdWorkstationWorker);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdWorkstationWorkerUpdateReqVO reqVO = randomPojo(MdWorkstationWorkerUpdateReqVO.class, o -> {
            o.setId(dbMdWorkstationWorker.getId()); // 设置更新的 ID
        });

        // 调用
        mdWorkstationWorkerService.updateMdWorkstationWorker(reqVO);
        // 校验是否更新正确
        MdWorkstationWorkerDO mdWorkstationWorker = mdWorkstationWorkerMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdWorkstationWorker);
    }

    @Test
    public void testUpdateMdWorkstationWorker_notExists() {
        // 准备参数
        MdWorkstationWorkerUpdateReqVO reqVO = randomPojo(MdWorkstationWorkerUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationWorkerService.updateMdWorkstationWorker(reqVO), MD_WORKSTATION_WORKER_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdWorkstationWorker_success() {
        // mock 数据
        MdWorkstationWorkerDO dbMdWorkstationWorker = randomPojo(MdWorkstationWorkerDO.class);
        mdWorkstationWorkerMapper.insert(dbMdWorkstationWorker);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdWorkstationWorker.getId();

        // 调用
        mdWorkstationWorkerService.deleteMdWorkstationWorker(id);
       // 校验数据不存在了
       assertNull(mdWorkstationWorkerMapper.selectById(id));
    }

    @Test
    public void testDeleteMdWorkstationWorker_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationWorkerService.deleteMdWorkstationWorker(id), MD_WORKSTATION_WORKER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationWorkerPage() {
       // mock 数据
       MdWorkstationWorkerDO dbMdWorkstationWorker = randomPojo(MdWorkstationWorkerDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setPostId(null);
           o.setPostCode(null);
           o.setPostName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationWorkerMapper.insert(dbMdWorkstationWorker);
       // 测试 workstationId 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setWorkstationId(null)));
       // 测试 postId 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostId(null)));
       // 测试 postCode 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostCode(null)));
       // 测试 postName 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostName(null)));
       // 测试 quantity 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationWorkerPageReqVO reqVO = new MdWorkstationWorkerPageReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setPostId(null);
       reqVO.setPostCode(null);
       reqVO.setPostName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdWorkstationWorkerDO> pageResult = mdWorkstationWorkerService.getMdWorkstationWorkerPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdWorkstationWorker, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationWorkerList() {
       // mock 数据
       MdWorkstationWorkerDO dbMdWorkstationWorker = randomPojo(MdWorkstationWorkerDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setPostId(null);
           o.setPostCode(null);
           o.setPostName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationWorkerMapper.insert(dbMdWorkstationWorker);
       // 测试 workstationId 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setWorkstationId(null)));
       // 测试 postId 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostId(null)));
       // 测试 postCode 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostCode(null)));
       // 测试 postName 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setPostName(null)));
       // 测试 quantity 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationWorkerMapper.insert(cloneIgnoreId(dbMdWorkstationWorker, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationWorkerExportReqVO reqVO = new MdWorkstationWorkerExportReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setPostId(null);
       reqVO.setPostCode(null);
       reqVO.setPostName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdWorkstationWorkerDO> list = mdWorkstationWorkerService.getMdWorkstationWorkerList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdWorkstationWorker, list.get(0));
    }

}
