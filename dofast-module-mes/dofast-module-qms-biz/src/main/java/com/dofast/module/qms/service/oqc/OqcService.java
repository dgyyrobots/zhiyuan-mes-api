package com.dofast.module.qms.service.oqc;

import java.util.*;
import javax.validation.*;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.qms.controller.admin.oqc.vo.*;
import com.dofast.module.qms.dal.dataobject.oqc.OqcDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 出货检验单 Service 接口
 *
 * @author 芋道源码
 */
public interface OqcService {
    public String checkOqcCodeUnique(OqcBaseVO qcIqc);
    public int updateCrMajMinQuaAndRate(Long oqcId);
    /**
     * 创建出货检验单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOqc(@Valid OqcCreateReqVO createReqVO);

    /**
     * 更新出货检验单
     *
     * @param updateReqVO 更新信息
     */
    void updateOqc(@Valid OqcUpdateReqVO updateReqVO);

    /**
     * 删除出货检验单
     *
     * @param id 编号
     */
    void deleteOqc(Long id);

    /**
     * 获得出货检验单
     *
     * @param id 编号
     * @return 出货检验单
     */
    OqcDO getOqc(Long id);

    /**
     * 获得出货检验单列表
     *
     * @param ids 编号
     * @return 出货检验单列表
     */
    List<OqcDO> getOqcList(Collection<Long> ids);

    /**
     * 获得出货检验单分页
     *
     * @param pageReqVO 分页查询
     * @return 出货检验单分页
     */
    PageResult<OqcDO> getOqcPage(OqcPageReqVO pageReqVO);

    /**
     * 获得出货检验单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 出货检验单列表
     */
    List<OqcDO> getOqcList(OqcExportReqVO exportReqVO);

    void generateLine(OqcBaseVO oqc);

    OqcDO generateOqc(FeedbackDTO feedback, WorkorderDTO workorderDTO);
}
