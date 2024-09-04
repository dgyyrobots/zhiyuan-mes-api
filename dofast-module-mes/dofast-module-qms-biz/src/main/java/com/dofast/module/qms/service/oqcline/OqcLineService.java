package com.dofast.module.qms.service.oqcline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.oqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.oqcline.OqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 出货检验单行 Service 接口
 *
 * @author 芋道源码
 */
public interface OqcLineService {
    /**
     * 计算并更新当前行的Cr,Maj,Min的总数量
     * @param qcId
     * @param lineId
     * @return
     */
    public int updateCrMajMinQuantity(Long qcId,Long lineId);
    /**
     * 根据出货检验单头删除相应的行信息
     * @param oqcId
     * @return
     */
    public int deleteByOqcId(Long oqcId);
    /**
     * 创建出货检验单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOqcLine(@Valid OqcLineCreateReqVO createReqVO);

    /**
     * 更新出货检验单行
     *
     * @param updateReqVO 更新信息
     */
    void updateOqcLine(@Valid OqcLineUpdateReqVO updateReqVO);

    /**
     * 删除出货检验单行
     *
     * @param id 编号
     */
    void deleteOqcLine(Long id);

    /**
     * 获得出货检验单行
     *
     * @param id 编号
     * @return 出货检验单行
     */
    OqcLineDO getOqcLine(Long id);

    /**
     * 获得出货检验单行列表
     *
     * @param ids 编号
     * @return 出货检验单行列表
     */
    List<OqcLineDO> getOqcLineList(Collection<Long> ids);

    /**
     * 获得出货检验单行分页
     *
     * @param pageReqVO 分页查询
     * @return 出货检验单行分页
     */
    PageResult<OqcLineDO> getOqcLinePage(OqcLinePageReqVO pageReqVO);

    /**
     * 获得出货检验单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 出货检验单行列表
     */
    List<OqcLineDO> getOqcLineList(OqcLineExportReqVO exportReqVO);

}
