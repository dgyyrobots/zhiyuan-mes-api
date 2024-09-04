package com.dofast.module.pro.service.workrecord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.workrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.workrecord.WorkrecordDO;
import com.dofast.module.pro.dal.mysql.workrecord.WorkrecordMapper;
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
 * {@link WorkrecordServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(WorkrecordServiceImpl.class)
public class WorkrecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WorkrecordServiceImpl workrecordService;

    @Resource
    private WorkrecordMapper workrecordMapper;

    @Test
    public void testCreateWorkrecord_success() {
        // 准备参数
        WorkrecordCreateReqVO reqVO = randomPojo(WorkrecordCreateReqVO.class);

        // 调用
        Long workrecordId = workrecordService.createWorkrecord(reqVO);
        // 断言
        assertNotNull(workrecordId);
        // 校验记录的属性是否正确
        WorkrecordDO workrecord = workrecordMapper.selectById(workrecordId);
        assertPojoEquals(reqVO, workrecord);
    }

    @Test
    public void testUpdateWorkrecord_success() {
        // mock 数据
        WorkrecordDO dbWorkrecord = randomPojo(WorkrecordDO.class);
        workrecordMapper.insert(dbWorkrecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WorkrecordUpdateReqVO reqVO = randomPojo(WorkrecordUpdateReqVO.class, o -> {
            o.setRecordId(dbWorkrecord.getRecordId()); // 设置更新的 ID
        });

        // 调用
        workrecordService.updateWorkrecord(reqVO);
        // 校验是否更新正确
        WorkrecordDO workrecord = workrecordMapper.selectById(reqVO.getRecordId()); // 获取最新的
        assertPojoEquals(reqVO, workrecord);
    }

    @Test
    public void testUpdateWorkrecord_notExists() {
        // 准备参数
        WorkrecordUpdateReqVO reqVO = randomPojo(WorkrecordUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> workrecordService.updateWorkrecord(reqVO), WORKRECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteWorkrecord_success() {
        // mock 数据
        WorkrecordDO dbWorkrecord = randomPojo(WorkrecordDO.class);
        workrecordMapper.insert(dbWorkrecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWorkrecord.getRecordId();

        // 调用
        workrecordService.deleteWorkrecord(id);
       // 校验数据不存在了
       assertNull(workrecordMapper.selectById(id));
    }

    @Test
    public void testDeleteWorkrecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> workrecordService.deleteWorkrecord(id), WORKRECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkrecordPage() {
       // mock 数据
       WorkrecordDO dbWorkrecord = randomPojo(WorkrecordDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setOperationFlag(null);
           o.setOperationTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateBy(null);
           o.setCreateTime(null);
           o.setUpdateBy(null);
       });
       workrecordMapper.insert(dbWorkrecord);
       // 测试 userId 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setNickName(null)));
       // 测试 workstationId 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationName(null)));
       // 测试 operationFlag 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setOperationFlag(null)));
       // 测试 operationTime 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setOperationTime(null)));
       // 测试 remark 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr4(null)));
       // 测试 createBy 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setCreateBy(null)));
       // 测试 createTime 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setCreateTime(null)));
       // 测试 updateBy 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUpdateBy(null)));
       // 准备参数
       WorkrecordPageReqVO reqVO = new WorkrecordPageReqVO();
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setOperationFlag(null);
       reqVO.setOperationTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateBy(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdateBy(null);

       // 调用
       PageResult<WorkrecordDO> pageResult = workrecordService.getWorkrecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWorkrecord, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkrecordList() {
       // mock 数据
       WorkrecordDO dbWorkrecord = randomPojo(WorkrecordDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setOperationFlag(null);
           o.setOperationTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateBy(null);
           o.setCreateTime(null);
           o.setUpdateBy(null);
       });
       workrecordMapper.insert(dbWorkrecord);
       // 测试 userId 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setNickName(null)));
       // 测试 workstationId 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setWorkstationName(null)));
       // 测试 operationFlag 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setOperationFlag(null)));
       // 测试 operationTime 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setOperationTime(null)));
       // 测试 remark 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setAttr4(null)));
       // 测试 createBy 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setCreateBy(null)));
       // 测试 createTime 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setCreateTime(null)));
       // 测试 updateBy 不匹配
       workrecordMapper.insert(cloneIgnoreId(dbWorkrecord, o -> o.setUpdateBy(null)));
       // 准备参数
       WorkrecordExportReqVO reqVO = new WorkrecordExportReqVO();
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setOperationFlag(null);
       reqVO.setOperationTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateBy(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdateBy(null);

       // 调用
       List<WorkrecordDO> list = workrecordService.getWorkrecordList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbWorkrecord, list.get(0));
    }

}
