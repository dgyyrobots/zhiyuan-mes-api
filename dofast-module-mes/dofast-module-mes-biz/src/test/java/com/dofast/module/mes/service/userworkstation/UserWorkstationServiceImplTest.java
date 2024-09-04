package com.dofast.module.mes.service.userworkstation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.userworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import com.dofast.module.mes.dal.mysql.userworkstation.UserWorkstationMapper;
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
 * {@link UserWorkstationServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(UserWorkstationServiceImpl.class)
public class UserWorkstationServiceImplTest extends BaseDbUnitTest {

    @Resource
    private UserWorkstationServiceImpl userWorkstationService;

    @Resource
    private UserWorkstationMapper userWorkstationMapper;

    @Test
    public void testCreateUserWorkstation_success() {
        // 准备参数
        UserWorkstationCreateReqVO reqVO = randomPojo(UserWorkstationCreateReqVO.class);

        // 调用
        Long userWorkstationId = userWorkstationService.createUserWorkstation(reqVO);
        // 断言
        assertNotNull(userWorkstationId);
        // 校验记录的属性是否正确
        UserWorkstationDO userWorkstation = userWorkstationMapper.selectById(userWorkstationId);
        assertPojoEquals(reqVO, userWorkstation);
    }

    @Test
    public void testUpdateUserWorkstation_success() {
        // mock 数据
        UserWorkstationDO dbUserWorkstation = randomPojo(UserWorkstationDO.class);
        userWorkstationMapper.insert(dbUserWorkstation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        UserWorkstationUpdateReqVO reqVO = randomPojo(UserWorkstationUpdateReqVO.class, o -> {
            o.setId(dbUserWorkstation.getId()); // 设置更新的 ID
        });

        // 调用
        userWorkstationService.updateUserWorkstation(reqVO);
        // 校验是否更新正确
        UserWorkstationDO userWorkstation = userWorkstationMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, userWorkstation);
    }

    @Test
    public void testUpdateUserWorkstation_notExists() {
        // 准备参数
        UserWorkstationUpdateReqVO reqVO = randomPojo(UserWorkstationUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> userWorkstationService.updateUserWorkstation(reqVO), USER_WORKSTATION_NOT_EXISTS);
    }

    @Test
    public void testDeleteUserWorkstation_success() {
        // mock 数据
        UserWorkstationDO dbUserWorkstation = randomPojo(UserWorkstationDO.class);
        userWorkstationMapper.insert(dbUserWorkstation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbUserWorkstation.getId();

        // 调用
        userWorkstationService.deleteUserWorkstation(id);
       // 校验数据不存在了
       assertNull(userWorkstationMapper.selectById(id));
    }

    @Test
    public void testDeleteUserWorkstation_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> userWorkstationService.deleteUserWorkstation(id), USER_WORKSTATION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetUserWorkstationPage() {
       // mock 数据
       UserWorkstationDO dbUserWorkstation = randomPojo(UserWorkstationDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setOperationTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       userWorkstationMapper.insert(dbUserWorkstation);
       // 测试 userId 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setNickName(null)));
       // 测试 workstationId 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationName(null)));
       // 测试 operationTime 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setOperationTime(null)));
       // 测试 remark 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setCreateTime(null)));
       // 准备参数
       UserWorkstationPageReqVO reqVO = new UserWorkstationPageReqVO();
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setOperationTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<UserWorkstationDO> pageResult = userWorkstationService.getUserWorkstationPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbUserWorkstation, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetUserWorkstationList() {
       // mock 数据
       UserWorkstationDO dbUserWorkstation = randomPojo(UserWorkstationDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setOperationTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       userWorkstationMapper.insert(dbUserWorkstation);
       // 测试 userId 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setNickName(null)));
       // 测试 workstationId 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setWorkstationName(null)));
       // 测试 operationTime 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setOperationTime(null)));
       // 测试 remark 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       userWorkstationMapper.insert(cloneIgnoreId(dbUserWorkstation, o -> o.setCreateTime(null)));
       // 准备参数
       UserWorkstationExportReqVO reqVO = new UserWorkstationExportReqVO();
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setOperationTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<UserWorkstationDO> list = userWorkstationService.getUserWorkstationList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbUserWorkstation, list.get(0));
    }

}
