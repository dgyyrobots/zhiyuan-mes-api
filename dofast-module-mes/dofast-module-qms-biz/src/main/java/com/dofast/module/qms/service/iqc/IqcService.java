package com.dofast.module.qms.service.iqc;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.iqc.vo.*;
import com.dofast.module.qms.dal.dataobject.iqc.IqcDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 来料检验单 Service 接口
 *
 * @author 芋道源码
 */
public interface IqcService {
    int updateCrMajMinQuaAndRate(Long iqcId);
    public String checkIqcCodeUnique(IqcBaseVO qcIqc);
    /**
     * 创建来料检验单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIqc(@Valid IqcCreateReqVO createReqVO);

    /**
     * 更新来料检验单
     *
     * @param updateReqVO 更新信息
     */
    void updateIqc(@Valid IqcUpdateReqVO updateReqVO);

    /**
     * 删除来料检验单
     *
     * @param id 编号
     */
    void deleteIqc(Long id);

    /**
     * 获得来料检验单
     *
     * @param id 编号
     * @return 来料检验单
     */
    IqcDO getIqc(Long id);

    /**
     * 获得来料检验单列表
     *
     * @param ids 编号
     * @return 来料检验单列表
     */
    List<IqcDO> getIqcList(Collection<Long> ids);

    /**
     * 获得来料检验单分页
     *
     * @param pageReqVO 分页查询
     * @return 来料检验单分页
     */
    PageResult<IqcDO> getIqcPage(IqcPageReqVO pageReqVO);

    /**
     * 获得来料检验单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 来料检验单列表
     */
    List<IqcDO> getIqcList(IqcExportReqVO exportReqVO);

}
