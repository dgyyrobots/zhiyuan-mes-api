package com.dofast.module.mes.service.mdproductsop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdproductsop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.module.mes.dal.mysql.mdproductsop.MdProductSopMapper;
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
 * {@link MdProductSopServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MdProductSopServiceImpl.class)
public class MdProductSopServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdProductSopServiceImpl mdProductSopService;

    @Resource
    private MdProductSopMapper mdProductSopMapper;

    @Test
    public void testCreateMdProductSop_success() {
        // 准备参数
        MdProductSopCreateReqVO reqVO = randomPojo(MdProductSopCreateReqVO.class);

        // 调用
        Long mdProductSopId = mdProductSopService.createMdProductSop(reqVO);
        // 断言
        assertNotNull(mdProductSopId);
        // 校验记录的属性是否正确
        MdProductSopDO mdProductSop = mdProductSopMapper.selectById(mdProductSopId);
        assertPojoEquals(reqVO, mdProductSop);
    }

    @Test
    public void testUpdateMdProductSop_success() {
        // mock 数据
        MdProductSopDO dbMdProductSop = randomPojo(MdProductSopDO.class);
        mdProductSopMapper.insert(dbMdProductSop);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdProductSopUpdateReqVO reqVO = randomPojo(MdProductSopUpdateReqVO.class, o -> {
            o.setId(dbMdProductSop.getId()); // 设置更新的 ID
        });

        // 调用
        mdProductSopService.updateMdProductSop(reqVO);
        // 校验是否更新正确
        MdProductSopDO mdProductSop = mdProductSopMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdProductSop);
    }

    @Test
    public void testUpdateMdProductSop_notExists() {
        // 准备参数
        MdProductSopUpdateReqVO reqVO = randomPojo(MdProductSopUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdProductSopService.updateMdProductSop(reqVO), MD_PRODUCT_SOP_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdProductSop_success() {
        // mock 数据
        MdProductSopDO dbMdProductSop = randomPojo(MdProductSopDO.class);
        mdProductSopMapper.insert(dbMdProductSop);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdProductSop.getId();

        // 调用
        mdProductSopService.deleteMdProductSop(id);
       // 校验数据不存在了
       assertNull(mdProductSopMapper.selectById(id));
    }

    @Test
    public void testDeleteMdProductSop_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdProductSopService.deleteMdProductSop(id), MD_PRODUCT_SOP_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdProductSopPage() {
       // mock 数据
       MdProductSopDO dbMdProductSop = randomPojo(MdProductSopDO.class, o -> { // 等会查询到
           o.setItemId(null);
           o.setOrderNum(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setSopTitle(null);
           o.setSopDescription(null);
           o.setSopUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdProductSopMapper.insert(dbMdProductSop);
       // 测试 itemId 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setItemId(null)));
       // 测试 orderNum 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setOrderNum(null)));
       // 测试 processId 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessName(null)));
       // 测试 sopTitle 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopTitle(null)));
       // 测试 sopDescription 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopDescription(null)));
       // 测试 sopUrl 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopUrl(null)));
       // 测试 remark 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setCreateTime(null)));
       // 准备参数
       MdProductSopPageReqVO reqVO = new MdProductSopPageReqVO();
       reqVO.setItemId(null);
       reqVO.setOrderNum(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setSopTitle(null);
       reqVO.setSopDescription(null);
       reqVO.setSopUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdProductSopDO> pageResult = mdProductSopService.getMdProductSopPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdProductSop, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdProductSopList() {
       // mock 数据
       MdProductSopDO dbMdProductSop = randomPojo(MdProductSopDO.class, o -> { // 等会查询到
           o.setItemId(null);
           o.setOrderNum(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setSopTitle(null);
           o.setSopDescription(null);
           o.setSopUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdProductSopMapper.insert(dbMdProductSop);
       // 测试 itemId 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setItemId(null)));
       // 测试 orderNum 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setOrderNum(null)));
       // 测试 processId 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setProcessName(null)));
       // 测试 sopTitle 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopTitle(null)));
       // 测试 sopDescription 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopDescription(null)));
       // 测试 sopUrl 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setSopUrl(null)));
       // 测试 remark 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdProductSopMapper.insert(cloneIgnoreId(dbMdProductSop, o -> o.setCreateTime(null)));
       // 准备参数
       MdProductSopExportReqVO reqVO = new MdProductSopExportReqVO();
       reqVO.setItemId(null);
       reqVO.setOrderNum(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setSopTitle(null);
       reqVO.setSopDescription(null);
       reqVO.setSopUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdProductSopDO> list = mdProductSopService.getMdProductSopList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdProductSop, list.get(0));
    }

}
