package com.dofast.module.mes.service.freezeinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.freezeinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.freezeinfo.FreezeInfoDO;
import com.dofast.module.mes.dal.mysql.freezeinfo.FreezeInfoMapper;
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
 * {@link FreezeInfoServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FreezeInfoServiceImpl.class)
public class FreezeInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FreezeInfoServiceImpl freezeInfoService;

    @Resource
    private FreezeInfoMapper freezeInfoMapper;

    @Test
    public void testCreateFreezeInfo_success() {
        // 准备参数
        FreezeInfoCreateReqVO reqVO = randomPojo(FreezeInfoCreateReqVO.class);

        // 调用
        Long freezeInfoId = freezeInfoService.createFreezeInfo(reqVO);
        // 断言
        assertNotNull(freezeInfoId);
        // 校验记录的属性是否正确
        FreezeInfoDO freezeInfo = freezeInfoMapper.selectById(freezeInfoId);
        assertPojoEquals(reqVO, freezeInfo);
    }

    @Test
    public void testUpdateFreezeInfo_success() {
        // mock 数据
        FreezeInfoDO dbFreezeInfo = randomPojo(FreezeInfoDO.class);
        freezeInfoMapper.insert(dbFreezeInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FreezeInfoUpdateReqVO reqVO = randomPojo(FreezeInfoUpdateReqVO.class, o -> {
            o.setId(dbFreezeInfo.getId()); // 设置更新的 ID
        });

        // 调用
        freezeInfoService.updateFreezeInfo(reqVO);
        // 校验是否更新正确
        FreezeInfoDO freezeInfo = freezeInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, freezeInfo);
    }

    @Test
    public void testUpdateFreezeInfo_notExists() {
        // 准备参数
        FreezeInfoUpdateReqVO reqVO = randomPojo(FreezeInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> freezeInfoService.updateFreezeInfo(reqVO), FREEZE_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteFreezeInfo_success() {
        // mock 数据
        FreezeInfoDO dbFreezeInfo = randomPojo(FreezeInfoDO.class);
        freezeInfoMapper.insert(dbFreezeInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFreezeInfo.getId();

        // 调用
        freezeInfoService.deleteFreezeInfo(id);
       // 校验数据不存在了
       assertNull(freezeInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteFreezeInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> freezeInfoService.deleteFreezeInfo(id), FREEZE_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFreezeInfoPage() {
       // mock 数据
       FreezeInfoDO dbFreezeInfo = randomPojo(FreezeInfoDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setItenQty(null);
           o.setState(null);
           o.setWhCode(null);
           o.setStorageCode(null);
           o.setFreezeMemo(null);
           o.setFreezer(null);
           o.setFreezeTime(null);
           o.setThawPerson(null);
           o.setThawMemo(null);
           o.setThawTime(null);
           o.setItenSn(null);
       });
       freezeInfoMapper.insert(dbFreezeInfo);
       // 测试 createTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItemName(null)));
       // 测试 itenQty 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItenQty(null)));
       // 测试 state 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setState(null)));
       // 测试 whCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setWhCode(null)));
       // 测试 storageCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setStorageCode(null)));
       // 测试 freezeMemo 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezeMemo(null)));
       // 测试 freezer 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezer(null)));
       // 测试 freezeTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezeTime(null)));
       // 测试 thawPerson 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawPerson(null)));
       // 测试 thawMemo 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawMemo(null)));
       // 测试 thawTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawTime(null)));
       // 测试 itenSn 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItenSn(null)));
       // 准备参数
       FreezeInfoPageReqVO reqVO = new FreezeInfoPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setItenQty(null);
       reqVO.setState(null);
       reqVO.setWhCode(null);
       reqVO.setStorageCode(null);
       reqVO.setFreezeMemo(null);
       reqVO.setFreezer(null);
       reqVO.setFreezeTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setThawPerson(null);
       reqVO.setThawMemo(null);
       reqVO.setThawTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItenSn(null);

       // 调用
       PageResult<FreezeInfoDO> pageResult = freezeInfoService.getFreezeInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFreezeInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFreezeInfoList() {
       // mock 数据
       FreezeInfoDO dbFreezeInfo = randomPojo(FreezeInfoDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setItenQty(null);
           o.setState(null);
           o.setWhCode(null);
           o.setStorageCode(null);
           o.setFreezeMemo(null);
           o.setFreezer(null);
           o.setFreezeTime(null);
           o.setThawPerson(null);
           o.setThawMemo(null);
           o.setThawTime(null);
           o.setItenSn(null);
       });
       freezeInfoMapper.insert(dbFreezeInfo);
       // 测试 createTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItemName(null)));
       // 测试 itenQty 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItenQty(null)));
       // 测试 state 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setState(null)));
       // 测试 whCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setWhCode(null)));
       // 测试 storageCode 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setStorageCode(null)));
       // 测试 freezeMemo 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezeMemo(null)));
       // 测试 freezer 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezer(null)));
       // 测试 freezeTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setFreezeTime(null)));
       // 测试 thawPerson 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawPerson(null)));
       // 测试 thawMemo 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawMemo(null)));
       // 测试 thawTime 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setThawTime(null)));
       // 测试 itenSn 不匹配
       freezeInfoMapper.insert(cloneIgnoreId(dbFreezeInfo, o -> o.setItenSn(null)));
       // 准备参数
       FreezeInfoExportReqVO reqVO = new FreezeInfoExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setItenQty(null);
       reqVO.setState(null);
       reqVO.setWhCode(null);
       reqVO.setStorageCode(null);
       reqVO.setFreezeMemo(null);
       reqVO.setFreezer(null);
       reqVO.setFreezeTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setThawPerson(null);
       reqVO.setThawMemo(null);
       reqVO.setThawTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItenSn(null);

       // 调用
       List<FreezeInfoDO> list = freezeInfoService.getFreezeInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFreezeInfo, list.get(0));
    }

}
