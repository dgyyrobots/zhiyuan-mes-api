package com.dofast.module.qms.service.iqcline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.iqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.iqcline.IqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 来料检验单行 Service 接口
 *
 * @author 芋道源码
 */
public interface IqcLineService {
    public int deleteByIqcId(Long iqcId);
    /**
     * 计算并更新当前行的Cr,Maj,Min的总数量
     * @param iqcId
     * @param lineId
     * @return
     */
    public int updateCrMajMinQuantity(Long iqcId,Long lineId);
    /**
     * 创建来料检验单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIqcLine(@Valid IqcLineCreateReqVO createReqVO);

    /**
     * 更新来料检验单行
     *
     * @param updateReqVO 更新信息
     */
    void updateIqcLine(@Valid IqcLineUpdateReqVO updateReqVO);

    /**
     * 删除来料检验单行
     *
     * @param id 编号
     */
    void deleteIqcLine(Long id);

    /**
     * 获得来料检验单行
     *
     * @param id 编号
     * @return 来料检验单行
     */
    IqcLineDO getIqcLine(Long id);

    /**
     * 获得来料检验单行列表
     *
     * @param ids 编号
     * @return 来料检验单行列表
     */
    List<IqcLineDO> getIqcLineList(Collection<Long> ids);

    /**
     * 获得来料检验单行分页
     *
     * @param pageReqVO 分页查询
     * @return 来料检验单行分页
     */
    PageResult<IqcLineDO> getIqcLinePage(IqcLinePageReqVO pageReqVO);

    /**
     * 获得来料检验单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 来料检验单行列表
     */
    List<IqcLineDO> getIqcLineList(IqcLineExportReqVO exportReqVO);

}
