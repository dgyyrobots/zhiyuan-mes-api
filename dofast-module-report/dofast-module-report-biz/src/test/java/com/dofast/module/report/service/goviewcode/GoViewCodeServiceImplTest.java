package com.dofast.module.report.service.goviewcode;

import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeCreateReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeExportReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodePageReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeUpdateReqVO;
import com.dofast.module.report.service.goview.GoViewCodeServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import com.dofast.module.report.dal.mysql.goview.GoViewCodeMapper;
import com.dofast.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import java.util.*;

import static com.dofast.module.report.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link GoViewCodeServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(GoViewCodeServiceImpl.class)
public class GoViewCodeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GoViewCodeServiceImpl goViewCodeService;

    @Resource
    private GoViewCodeMapper goViewCodeMapper;

    @Test
    public void testCreateGoViewCode_success() {
        // 准备参数
        GoViewCodeCreateReqVO reqVO = randomPojo(GoViewCodeCreateReqVO.class);

        // 调用
        Long goViewCodeId = goViewCodeService.createGoViewCode(reqVO);
        // 断言
        assertNotNull(goViewCodeId);
        // 校验记录的属性是否正确
        GoViewCodeDO goViewCode = goViewCodeMapper.selectById(goViewCodeId);
        assertPojoEquals(reqVO, goViewCode);
    }

    @Test
    public void testUpdateGoViewCode_success() {
        // mock 数据
        GoViewCodeDO dbGoViewCode = randomPojo(GoViewCodeDO.class);
        goViewCodeMapper.insert(dbGoViewCode);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GoViewCodeUpdateReqVO reqVO = randomPojo(GoViewCodeUpdateReqVO.class, o -> {
            o.setId(dbGoViewCode.getId()); // 设置更新的 ID
        });

        // 调用
        goViewCodeService.updateGoViewCode(reqVO);
        // 校验是否更新正确
        GoViewCodeDO goViewCode = goViewCodeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, goViewCode);
    }

    @Test
    public void testUpdateGoViewCode_notExists() {
        // 准备参数
        GoViewCodeUpdateReqVO reqVO = randomPojo(GoViewCodeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> goViewCodeService.updateGoViewCode(reqVO), GO_VIEW_CODE_NOT_EXISTS);
    }

    @Test
    public void testDeleteGoViewCode_success() {
        // mock 数据
        GoViewCodeDO dbGoViewCode = randomPojo(GoViewCodeDO.class);
        goViewCodeMapper.insert(dbGoViewCode);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbGoViewCode.getId();

        // 调用
        goViewCodeService.deleteGoViewCode(id);
       // 校验数据不存在了
       assertNull(goViewCodeMapper.selectById(id));
    }

    @Test
    public void testDeleteGoViewCode_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> goViewCodeService.deleteGoViewCode(id), GO_VIEW_CODE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGoViewCodePage() {
       // mock 数据
       GoViewCodeDO dbGoViewCode = randomPojo(GoViewCodeDO.class, o -> { // 等会查询到
           o.setStatus(null);
           o.setExpireTime(null);
           o.setPdaUserId(null);
           o.setPdaToken(null);
           o.setGoviewToken(null);
           o.setCreateTime(null);
       });
       goViewCodeMapper.insert(dbGoViewCode);
       // 测试 status 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setStatus(null)));
       // 测试 expireTime 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setExpireTime(null)));
       // 测试 pdaUserId 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setPdaUserId(null)));
       // 测试 pdaToken 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setPdaToken(null)));
       // 测试 goviewToken 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setGoviewToken(null)));
       // 测试 createTime 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setCreateTime(null)));
       // 准备参数
       GoViewCodePageReqVO reqVO = new GoViewCodePageReqVO();
       reqVO.setStatus(null);
       reqVO.setExpireTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setPdaUserId(null);
       reqVO.setPdaToken(null);
       reqVO.setGoviewToken(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<GoViewCodeDO> pageResult = goViewCodeService.getGoViewCodePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbGoViewCode, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGoViewCodeList() {
       // mock 数据
       GoViewCodeDO dbGoViewCode = randomPojo(GoViewCodeDO.class, o -> { // 等会查询到
           o.setStatus(null);
           o.setExpireTime(null);
           o.setPdaUserId(null);
           o.setPdaToken(null);
           o.setGoviewToken(null);
           o.setCreateTime(null);
       });
       goViewCodeMapper.insert(dbGoViewCode);
       // 测试 status 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setStatus(null)));
       // 测试 expireTime 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setExpireTime(null)));
       // 测试 pdaUserId 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setPdaUserId(null)));
       // 测试 pdaToken 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setPdaToken(null)));
       // 测试 goviewToken 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setGoviewToken(null)));
       // 测试 createTime 不匹配
       goViewCodeMapper.insert(cloneIgnoreId(dbGoViewCode, o -> o.setCreateTime(null)));
       // 准备参数
       GoViewCodeExportReqVO reqVO = new GoViewCodeExportReqVO();
       reqVO.setStatus(null);
       reqVO.setExpireTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setPdaUserId(null);
       reqVO.setPdaToken(null);
       reqVO.setGoviewToken(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<GoViewCodeDO> list = goViewCodeService.getGoViewCodeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbGoViewCode, list.get(0));
    }

}
