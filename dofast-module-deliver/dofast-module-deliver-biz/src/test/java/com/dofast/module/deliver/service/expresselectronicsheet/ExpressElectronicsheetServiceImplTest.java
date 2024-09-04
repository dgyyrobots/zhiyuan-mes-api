package com.dofast.module.deliver.service.expresselectronicsheet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;
import com.dofast.module.deliver.dal.mysql.expresselectronicsheet.ExpressElectronicsheetMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.deliver.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ExpressElectronicsheetServiceImpl} 的单元测试类
 *
 * @author a1
 */
@Import(ExpressElectronicsheetServiceImpl.class)
public class ExpressElectronicsheetServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ExpressElectronicsheetServiceImpl expressElectronicsheetService;

    @Resource
    private ExpressElectronicsheetMapper expressElectronicsheetMapper;

    @Test
    public void testCreateExpressElectronicsheet_success() {
        // 准备参数
        ExpressElectronicsheetCreateReqVO reqVO = randomPojo(ExpressElectronicsheetCreateReqVO.class);

        // 调用
        Long expressElectronicsheetId = expressElectronicsheetService.createExpressElectronicsheet(reqVO);
        // 断言
        assertNotNull(expressElectronicsheetId);
        // 校验记录的属性是否正确
        ExpressElectronicsheetDO expressElectronicsheet = expressElectronicsheetMapper.selectById(expressElectronicsheetId);
        assertPojoEquals(reqVO, expressElectronicsheet);
    }

    @Test
    public void testUpdateExpressElectronicsheet_success() {
        // mock 数据
        ExpressElectronicsheetDO dbExpressElectronicsheet = randomPojo(ExpressElectronicsheetDO.class);
        expressElectronicsheetMapper.insert(dbExpressElectronicsheet);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ExpressElectronicsheetUpdateReqVO reqVO = randomPojo(ExpressElectronicsheetUpdateReqVO.class, o -> {
            o.setId(dbExpressElectronicsheet.getId()); // 设置更新的 ID
        });

        // 调用
        expressElectronicsheetService.updateExpressElectronicsheet(reqVO);
        // 校验是否更新正确
        ExpressElectronicsheetDO expressElectronicsheet = expressElectronicsheetMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, expressElectronicsheet);
    }

    @Test
    public void testUpdateExpressElectronicsheet_notExists() {
        // 准备参数
        ExpressElectronicsheetUpdateReqVO reqVO = randomPojo(ExpressElectronicsheetUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> expressElectronicsheetService.updateExpressElectronicsheet(reqVO), EXPRESS_ELECTRONICSHEET_NOT_EXISTS);
    }

    @Test
    public void testDeleteExpressElectronicsheet_success() {
        // mock 数据
        ExpressElectronicsheetDO dbExpressElectronicsheet = randomPojo(ExpressElectronicsheetDO.class);
        expressElectronicsheetMapper.insert(dbExpressElectronicsheet);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbExpressElectronicsheet.getId();

        // 调用
        expressElectronicsheetService.deleteExpressElectronicsheet(id);
       // 校验数据不存在了
       assertNull(expressElectronicsheetMapper.selectById(id));
    }

    @Test
    public void testDeleteExpressElectronicsheet_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> expressElectronicsheetService.deleteExpressElectronicsheet(id), EXPRESS_ELECTRONICSHEET_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetExpressElectronicsheetPage() {
       // mock 数据
       ExpressElectronicsheetDO dbExpressElectronicsheet = randomPojo(ExpressElectronicsheetDO.class, o -> { // 等会查询到
           o.setCompanyName(null);
           o.setType(null);
           o.setInfo(null);
           o.setKdnCode(null);
           o.setTemplate(null);
           o.setConfig(null);
           o.setCreateTime(null);
       });
       expressElectronicsheetMapper.insert(dbExpressElectronicsheet);
       // 测试 companyName 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setCompanyName(null)));
       // 测试 type 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setType(null)));
       // 测试 info 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setInfo(null)));
       // 测试 kdnCode 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setKdnCode(null)));
       // 测试 template 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setTemplate(null)));
       // 测试 config 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setConfig(null)));
       // 测试 createTime 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setCreateTime(null)));
       // 准备参数
       ExpressElectronicsheetPageReqVO reqVO = new ExpressElectronicsheetPageReqVO();
       reqVO.setCompanyName(null);
       reqVO.setType(null);
       reqVO.setInfo(null);
       reqVO.setKdnCode(null);
       reqVO.setTemplate(null);
       reqVO.setConfig(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ExpressElectronicsheetDO> pageResult = expressElectronicsheetService.getExpressElectronicsheetPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbExpressElectronicsheet, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetExpressElectronicsheetList() {
       // mock 数据
       ExpressElectronicsheetDO dbExpressElectronicsheet = randomPojo(ExpressElectronicsheetDO.class, o -> { // 等会查询到
           o.setCompanyName(null);
           o.setType(null);
           o.setInfo(null);
           o.setKdnCode(null);
           o.setTemplate(null);
           o.setConfig(null);
           o.setCreateTime(null);
       });
       expressElectronicsheetMapper.insert(dbExpressElectronicsheet);
       // 测试 companyName 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setCompanyName(null)));
       // 测试 type 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setType(null)));
       // 测试 info 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setInfo(null)));
       // 测试 kdnCode 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setKdnCode(null)));
       // 测试 template 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setTemplate(null)));
       // 测试 config 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setConfig(null)));
       // 测试 createTime 不匹配
       expressElectronicsheetMapper.insert(cloneIgnoreId(dbExpressElectronicsheet, o -> o.setCreateTime(null)));
       // 准备参数
       ExpressElectronicsheetExportReqVO reqVO = new ExpressElectronicsheetExportReqVO();
       reqVO.setCompanyName(null);
       reqVO.setType(null);
       reqVO.setInfo(null);
       reqVO.setKdnCode(null);
       reqVO.setTemplate(null);
       reqVO.setConfig(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ExpressElectronicsheetDO> list = expressElectronicsheetService.getExpressElectronicsheetList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbExpressElectronicsheet, list.get(0));
    }

}
