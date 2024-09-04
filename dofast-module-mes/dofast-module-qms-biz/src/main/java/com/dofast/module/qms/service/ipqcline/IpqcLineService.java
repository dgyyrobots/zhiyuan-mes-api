package com.dofast.module.qms.service.ipqcline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.ipqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqcline.IpqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 过程检验单行 Service 接口
 *
 * @author 芋道源码
 */
public interface IpqcLineService {
    /**
     * 计算并更新当前行的Cr,Maj,Min的总数量
     * @param qcId
     * @param lineId
     * @return
     */
    public int updateCrMajMinQuantity(Long qcId,Long lineId);
    /***
     * 根据检验单头ID删除所有行信息
     * @param ipqcId
     * @return
     */
    public int deleteByIpqcId(Long ipqcId);
    /**
     * 创建过程检验单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIpqcLine(@Valid IpqcLineCreateReqVO createReqVO);

    /**
     * 更新过程检验单行
     *
     * @param updateReqVO 更新信息
     */
    void updateIpqcLine(@Valid IpqcLineUpdateReqVO updateReqVO);

    /**
     * 删除过程检验单行
     *
     * @param id 编号
     */
    void deleteIpqcLine(Long id);

    /**
     * 获得过程检验单行
     *
     * @param id 编号
     * @return 过程检验单行
     */
    IpqcLineDO getIpqcLine(Long id);

    /**
     * 获得过程检验单行列表
     *
     * @param ids 编号
     * @return 过程检验单行列表
     */
    List<IpqcLineDO> getIpqcLineList(Collection<Long> ids);

    /**
     * 获得过程检验单行分页
     *
     * @param pageReqVO 分页查询
     * @return 过程检验单行分页
     */
    PageResult<IpqcLineDO> getIpqcLinePage(IpqcLinePageReqVO pageReqVO);

    /**
     * 获得过程检验单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 过程检验单行列表
     */
    List<IpqcLineDO> getIpqcLineList(IpqcLineExportReqVO exportReqVO);

}
