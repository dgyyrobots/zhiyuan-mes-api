package com.dofast.module.mes.service.mdunitconverse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import com.dofast.module.mes.dal.mysql.mdunitconverse.MdUnitConverseMapper;
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
 * {@link MdUnitConverseServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(MdUnitConverseServiceImpl.class)
public class MdUnitConverseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdUnitConverseServiceImpl mdUnitConverseService;

    @Resource
    private MdUnitConverseMapper mdUnitConverseMapper;

    @Test
    public void testCreateMdUnitConverse_success() {
        // 准备参数
        MdUnitConverseCreateReqVO reqVO = randomPojo(MdUnitConverseCreateReqVO.class);

        // 调用
        Long mdUnitConverseId = mdUnitConverseService.createMdUnitConverse(reqVO);
        // 断言
        assertNotNull(mdUnitConverseId);
        // 校验记录的属性是否正确
        MdUnitConverseDO mdUnitConverse = mdUnitConverseMapper.selectById(mdUnitConverseId);
        assertPojoEquals(reqVO, mdUnitConverse);
    }

    @Test
    public void testUpdateMdUnitConverse_success() {
        // mock 数据
        MdUnitConverseDO dbMdUnitConverse = randomPojo(MdUnitConverseDO.class);
        mdUnitConverseMapper.insert(dbMdUnitConverse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdUnitConverseUpdateReqVO reqVO = randomPojo(MdUnitConverseUpdateReqVO.class, o -> {
            o.setId(dbMdUnitConverse.getId()); // 设置更新的 ID
        });

        // 调用
        mdUnitConverseService.updateMdUnitConverse(reqVO);
        // 校验是否更新正确
        MdUnitConverseDO mdUnitConverse = mdUnitConverseMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdUnitConverse);
    }

    @Test
    public void testUpdateMdUnitConverse_notExists() {
        // 准备参数
        MdUnitConverseUpdateReqVO reqVO = randomPojo(MdUnitConverseUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdUnitConverseService.updateMdUnitConverse(reqVO), MD_UNIT_CONVERSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdUnitConverse_success() {
        // mock 数据
        MdUnitConverseDO dbMdUnitConverse = randomPojo(MdUnitConverseDO.class);
        mdUnitConverseMapper.insert(dbMdUnitConverse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdUnitConverse.getId();

        // 调用
        mdUnitConverseService.deleteMdUnitConverse(id);
       // 校验数据不存在了
       assertNull(mdUnitConverseMapper.selectById(id));
    }

    @Test
    public void testDeleteMdUnitConverse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdUnitConverseService.deleteMdUnitConverse(id), MD_UNIT_CONVERSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdUnitConversePage() {
       // mock 数据
       MdUnitConverseDO dbMdUnitConverse = randomPojo(MdUnitConverseDO.class, o -> { // 等会查询到
           o.setMeasureCode(null);
           o.setOriginCount(null);
           o.setConverseCode(null);
           o.setConverseCount(null);
           o.setCreateTime(null);
       });
       mdUnitConverseMapper.insert(dbMdUnitConverse);
       // 测试 measureCode 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setMeasureCode(null)));
       // 测试 originCount 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setOriginCount(null)));
       // 测试 converseCode 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setConverseCode(null)));
       // 测试 converseCount 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setConverseCount(null)));
       // 测试 createTime 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setCreateTime(null)));
       // 准备参数
       MdUnitConversePageReqVO reqVO = new MdUnitConversePageReqVO();
       reqVO.setMeasureCode(null);
       reqVO.setOriginCount(null);
       reqVO.setConverseCode(null);
       reqVO.setConverseCount(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdUnitConverseDO> pageResult = mdUnitConverseService.getMdUnitConversePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdUnitConverse, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdUnitConverseList() {
       // mock 数据
       MdUnitConverseDO dbMdUnitConverse = randomPojo(MdUnitConverseDO.class, o -> { // 等会查询到
           o.setMeasureCode(null);
           o.setOriginCount(null);
           o.setConverseCode(null);
           o.setConverseCount(null);
           o.setCreateTime(null);
       });
       mdUnitConverseMapper.insert(dbMdUnitConverse);
       // 测试 measureCode 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setMeasureCode(null)));
       // 测试 originCount 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setOriginCount(null)));
       // 测试 converseCode 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setConverseCode(null)));
       // 测试 converseCount 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setConverseCount(null)));
       // 测试 createTime 不匹配
       mdUnitConverseMapper.insert(cloneIgnoreId(dbMdUnitConverse, o -> o.setCreateTime(null)));
       // 准备参数
       MdUnitConverseExportReqVO reqVO = new MdUnitConverseExportReqVO();
       reqVO.setMeasureCode(null);
       reqVO.setOriginCount(null);
       reqVO.setConverseCode(null);
       reqVO.setConverseCount(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdUnitConverseDO> list = mdUnitConverseService.getMdUnitConverseList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdUnitConverse, list.get(0));
    }

}
