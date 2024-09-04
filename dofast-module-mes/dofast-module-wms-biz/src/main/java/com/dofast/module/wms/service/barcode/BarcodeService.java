package com.dofast.module.wms.service.barcode;

import java.io.IOException;
import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.barcode.vo.*;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.framework.common.pojo.PageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 条码清单 Service 接口
 *
 * @author 芋道源码
 */
public interface BarcodeService {
    /**
     * 根据条码记录生成实际的条码，返回对应的url地址
     * @param baseVO
     * @return
     */
    public String generateBarcode(BarcodeBaseVO baseVO) throws IOException;
    /**
     * 根据条码类型和业务内容ID判断条码是否已存在
     * @param baseVO
     * @return
     */
    public String checkBarcodeUnique(BarcodeBaseVO baseVO);
    /**
     * 创建条码清单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBarcode(@Valid BarcodeCreateReqVO createReqVO);

    /**
     * 更新条码清单
     *
     * @param updateReqVO 更新信息
     */
    void updateBarcode(@Valid BarcodeUpdateReqVO updateReqVO);

    /**
     * 删除条码清单
     *
     * @param id 编号
     */
    void deleteBarcode(Long id);

    /**
     * 获得条码清单
     *
     * @param id 编号
     * @return 条码清单
     */
    BarcodeDO getBarcode(Long id);

    /**
     * 获得条码清单列表
     *
     * @param ids 编号
     * @return 条码清单列表
     */
    List<BarcodeDO> getBarcodeList(Collection<Long> ids);

    /**
     * 获得条码清单分页
     *
     * @param pageReqVO 分页查询
     * @return 条码清单分页
     */
    PageResult<BarcodeDO> getBarcodePage(BarcodePageReqVO pageReqVO);

    /**
     * 获得条码清单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 条码清单列表
     */
    List<BarcodeDO> getBarcodeList(BarcodeExportReqVO exportReqVO);

}
