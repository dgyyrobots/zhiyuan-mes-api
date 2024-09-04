package com.dofast.module.mes.service.defectiveinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.defectiveinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.defectiveinfo.DefectiveInfoDO;
import com.dofast.module.mes.dal.mysql.defectiveinfo.DefectiveInfoMapper;
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
 * {@link DefectiveInfoServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(DefectiveInfoServiceImpl.class)
public class DefectiveInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DefectiveInfoServiceImpl defectiveInfoService;

    @Resource
    private DefectiveInfoMapper defectiveInfoMapper;

    @Test
    public void testCreateDefectiveInfo_success() {
        // 准备参数
        DefectiveInfoCreateReqVO reqVO = randomPojo(DefectiveInfoCreateReqVO.class);

        // 调用
        Long defectiveInfoId = defectiveInfoService.createDefectiveInfo(reqVO);
        // 断言
        assertNotNull(defectiveInfoId);
        // 校验记录的属性是否正确
        DefectiveInfoDO defectiveInfo = defectiveInfoMapper.selectById(defectiveInfoId);
        assertPojoEquals(reqVO, defectiveInfo);
    }

    @Test
    public void testUpdateDefectiveInfo_success() {
        // mock 数据
        DefectiveInfoDO dbDefectiveInfo = randomPojo(DefectiveInfoDO.class);
        defectiveInfoMapper.insert(dbDefectiveInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DefectiveInfoUpdateReqVO reqVO = randomPojo(DefectiveInfoUpdateReqVO.class, o -> {
            o.setId(dbDefectiveInfo.getId()); // 设置更新的 ID
        });

        // 调用
        defectiveInfoService.updateDefectiveInfo(reqVO);
        // 校验是否更新正确
        DefectiveInfoDO defectiveInfo = defectiveInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, defectiveInfo);
    }

    @Test
    public void testUpdateDefectiveInfo_notExists() {
        // 准备参数
        DefectiveInfoUpdateReqVO reqVO = randomPojo(DefectiveInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> defectiveInfoService.updateDefectiveInfo(reqVO), DEFECTIVE_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteDefectiveInfo_success() {
        // mock 数据
        DefectiveInfoDO dbDefectiveInfo = randomPojo(DefectiveInfoDO.class);
        defectiveInfoMapper.insert(dbDefectiveInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDefectiveInfo.getId();

        // 调用
        defectiveInfoService.deleteDefectiveInfo(id);
       // 校验数据不存在了
       assertNull(defectiveInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteDefectiveInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> defectiveInfoService.deleteDefectiveInfo(id), DEFECTIVE_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectiveInfoPage() {
       // mock 数据
       DefectiveInfoDO dbDefectiveInfo = randomPojo(DefectiveInfoDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setBatchesCode(null);
           o.setBatches(null);
           o.setBadDescription(null);
           o.setQuantity(null);
           o.setBadQuantity(null);
           o.setInspectQty(null);
           o.setErrorCodeName(null);
           o.setBadTime(null);
           o.setEntryPerson(null);
           o.setEntryTime(null);
           o.setReinspector(null);
           o.setReinspectDate(null);
           o.setReinspectConclusion(null);
           o.setInspectGroup(null);
           o.setExcuteState(null);
           o.setExcuteCode(null);
       });
       defectiveInfoMapper.insert(dbDefectiveInfo);
       // 测试 createTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setItemName(null)));
       // 测试 batchesCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBatchesCode(null)));
       // 测试 batches 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBatches(null)));
       // 测试 badDescription 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadDescription(null)));
       // 测试 quantity 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setQuantity(null)));
       // 测试 badQuantity 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadQuantity(null)));
       // 测试 inspectQty 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setInspectQty(null)));
       // 测试 errorCodeName 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setErrorCodeName(null)));
       // 测试 badTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadTime(null)));
       // 测试 entryPerson 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setEntryPerson(null)));
       // 测试 entryTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setEntryTime(null)));
       // 测试 reinspector 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspector(null)));
       // 测试 reinspectDate 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspectDate(null)));
       // 测试 reinspectConclusion 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspectConclusion(null)));
       // 测试 inspectGroup 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setInspectGroup(null)));
       // 测试 excuteState 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setExcuteState(null)));
       // 测试 excuteCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setExcuteCode(null)));
       // 准备参数
       DefectiveInfoPageReqVO reqVO = new DefectiveInfoPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setBatchesCode(null);
       reqVO.setBatches(null);
       reqVO.setBadDescription(null);
       reqVO.setQuantity(null);
       reqVO.setBadQuantity(null);
       reqVO.setInspectQty(null);
       reqVO.setErrorCodeName(null);
       reqVO.setBadTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEntryPerson(null);
       reqVO.setEntryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspector(null);
       reqVO.setReinspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspectConclusion(null);
       reqVO.setInspectGroup(null);
       reqVO.setExcuteState(null);
       reqVO.setExcuteCode(null);

       // 调用
       PageResult<DefectiveInfoDO> pageResult = defectiveInfoService.getDefectiveInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDefectiveInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectiveInfoList() {
       // mock 数据
       DefectiveInfoDO dbDefectiveInfo = randomPojo(DefectiveInfoDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setBatchesCode(null);
           o.setBatches(null);
           o.setBadDescription(null);
           o.setQuantity(null);
           o.setBadQuantity(null);
           o.setInspectQty(null);
           o.setErrorCodeName(null);
           o.setBadTime(null);
           o.setEntryPerson(null);
           o.setEntryTime(null);
           o.setReinspector(null);
           o.setReinspectDate(null);
           o.setReinspectConclusion(null);
           o.setInspectGroup(null);
           o.setExcuteState(null);
           o.setExcuteCode(null);
       });
       defectiveInfoMapper.insert(dbDefectiveInfo);
       // 测试 createTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setItemName(null)));
       // 测试 batchesCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBatchesCode(null)));
       // 测试 batches 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBatches(null)));
       // 测试 badDescription 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadDescription(null)));
       // 测试 quantity 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setQuantity(null)));
       // 测试 badQuantity 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadQuantity(null)));
       // 测试 inspectQty 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setInspectQty(null)));
       // 测试 errorCodeName 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setErrorCodeName(null)));
       // 测试 badTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setBadTime(null)));
       // 测试 entryPerson 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setEntryPerson(null)));
       // 测试 entryTime 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setEntryTime(null)));
       // 测试 reinspector 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspector(null)));
       // 测试 reinspectDate 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspectDate(null)));
       // 测试 reinspectConclusion 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setReinspectConclusion(null)));
       // 测试 inspectGroup 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setInspectGroup(null)));
       // 测试 excuteState 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setExcuteState(null)));
       // 测试 excuteCode 不匹配
       defectiveInfoMapper.insert(cloneIgnoreId(dbDefectiveInfo, o -> o.setExcuteCode(null)));
       // 准备参数
       DefectiveInfoExportReqVO reqVO = new DefectiveInfoExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setBatchesCode(null);
       reqVO.setBatches(null);
       reqVO.setBadDescription(null);
       reqVO.setQuantity(null);
       reqVO.setBadQuantity(null);
       reqVO.setInspectQty(null);
       reqVO.setErrorCodeName(null);
       reqVO.setBadTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEntryPerson(null);
       reqVO.setEntryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspector(null);
       reqVO.setReinspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspectConclusion(null);
       reqVO.setInspectGroup(null);
       reqVO.setExcuteState(null);
       reqVO.setExcuteCode(null);

       // 调用
       List<DefectiveInfoDO> list = defectiveInfoService.getDefectiveInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDefectiveInfo, list.get(0));
    }

}
