package com.dofast.module.wms.service.barcode;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.barcode.vo.*;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.module.wms.dal.mysql.barcode.BarcodeMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link BarcodeServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(BarcodeServiceImpl.class)
public class BarcodeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BarcodeServiceImpl barcodeService;

    @Resource
    private BarcodeMapper barcodeMapper;

    @Test
    public void testCreateBarcode_success() {
        // 准备参数
        BarcodeCreateReqVO reqVO = randomPojo(BarcodeCreateReqVO.class);

        // 调用
        Long barcodeId = barcodeService.createBarcode(reqVO);
        // 断言
        assertNotNull(barcodeId);
        // 校验记录的属性是否正确
        BarcodeDO barcode = barcodeMapper.selectById(barcodeId);
        assertPojoEquals(reqVO, barcode);
    }

    @Test
    public void testUpdateBarcode_success() {
        // mock 数据
        BarcodeDO dbBarcode = randomPojo(BarcodeDO.class);
        barcodeMapper.insert(dbBarcode);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BarcodeUpdateReqVO reqVO = randomPojo(BarcodeUpdateReqVO.class, o -> {
            o.setId(dbBarcode.getId()); // 设置更新的 ID
        });

        // 调用
        barcodeService.updateBarcode(reqVO);
        // 校验是否更新正确
        BarcodeDO barcode = barcodeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, barcode);
    }

    @Test
    public void testUpdateBarcode_notExists() {
        // 准备参数
        BarcodeUpdateReqVO reqVO = randomPojo(BarcodeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> barcodeService.updateBarcode(reqVO), BARCODE_NOT_EXISTS);
    }

    @Test
    public void testDeleteBarcode_success() {
        // mock 数据
        BarcodeDO dbBarcode = randomPojo(BarcodeDO.class);
        barcodeMapper.insert(dbBarcode);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBarcode.getId();

        // 调用
        barcodeService.deleteBarcode(id);
       // 校验数据不存在了
       assertNull(barcodeMapper.selectById(id));
    }

    @Test
    public void testDeleteBarcode_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> barcodeService.deleteBarcode(id), BARCODE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBarcodePage() {
       // mock 数据
       BarcodeDO dbBarcode = randomPojo(BarcodeDO.class, o -> { // 等会查询到
           o.setBarcodeFormart(null);
           o.setBarcodeType(null);
           o.setBarcodeContent(null);
           o.setBussinessId(null);
           o.setBussinessCode(null);
           o.setBussinessName(null);
           o.setBarcodeUrl(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       barcodeMapper.insert(dbBarcode);
       // 测试 barcodeFormart 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeFormart(null)));
       // 测试 barcodeType 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeType(null)));
       // 测试 barcodeContent 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeContent(null)));
       // 测试 bussinessId 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessId(null)));
       // 测试 bussinessCode 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessCode(null)));
       // 测试 bussinessName 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessName(null)));
       // 测试 barcodeUrl 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeUrl(null)));
       // 测试 enableFlag 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setCreateTime(null)));
       // 准备参数
       BarcodePageReqVO reqVO = new BarcodePageReqVO();
       reqVO.setBarcodeFormart(null);
       reqVO.setBarcodeType(null);
       reqVO.setBarcodeContent(null);
       reqVO.setBussinessId(null);
       reqVO.setBussinessCode(null);
       reqVO.setBussinessName(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BarcodeDO> pageResult = barcodeService.getBarcodePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBarcode, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBarcodeList() {
       // mock 数据
       BarcodeDO dbBarcode = randomPojo(BarcodeDO.class, o -> { // 等会查询到
           o.setBarcodeFormart(null);
           o.setBarcodeType(null);
           o.setBarcodeContent(null);
           o.setBussinessId(null);
           o.setBussinessCode(null);
           o.setBussinessName(null);
           o.setBarcodeUrl(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       barcodeMapper.insert(dbBarcode);
       // 测试 barcodeFormart 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeFormart(null)));
       // 测试 barcodeType 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeType(null)));
       // 测试 barcodeContent 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeContent(null)));
       // 测试 bussinessId 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessId(null)));
       // 测试 bussinessCode 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessCode(null)));
       // 测试 bussinessName 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBussinessName(null)));
       // 测试 barcodeUrl 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setBarcodeUrl(null)));
       // 测试 enableFlag 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       barcodeMapper.insert(cloneIgnoreId(dbBarcode, o -> o.setCreateTime(null)));
       // 准备参数
       BarcodeExportReqVO reqVO = new BarcodeExportReqVO();
       reqVO.setBarcodeFormart(null);
       reqVO.setBarcodeType(null);
       reqVO.setBarcodeContent(null);
       reqVO.setBussinessId(null);
       reqVO.setBussinessCode(null);
       reqVO.setBussinessName(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<BarcodeDO> list = barcodeService.getBarcodeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBarcode, list.get(0));
    }

}
