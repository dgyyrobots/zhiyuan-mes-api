package com.dofast.module.mes.service.mdunitconverse;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 单位换算 Service 接口
 *
 * @author 惠智造
 */
public interface MdUnitConverseService {

    /**
     * 创建单位换算
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdUnitConverse(@Valid MdUnitConverseCreateReqVO createReqVO);

    /**
     * 更新单位换算
     *
     * @param updateReqVO 更新信息
     */
    void updateMdUnitConverse(@Valid MdUnitConverseUpdateReqVO updateReqVO);

    /**
     * 删除单位换算
     *
     * @param id 编号
     */
    void deleteMdUnitConverse(Long id);

    /**
     * 获得单位换算
     *
     * @param id 编号
     * @return 单位换算
     */
    MdUnitConverseDO getMdUnitConverse(Long id);

    /**
     * 获得单位换算列表
     *
     * @param ids 编号
     * @return 单位换算列表
     */
    List<MdUnitConverseDO> getMdUnitConverseList(Collection<Long> ids);

    /**
     * 获得单位换算分页
     *
     * @param pageReqVO 分页查询
     * @return 单位换算分页
     */
    PageResult<MdUnitConverseDO> getMdUnitConversePage(MdUnitConversePageReqVO pageReqVO);

    /**
     * 获得单位换算列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 单位换算列表
     */
    List<MdUnitConverseDO> getMdUnitConverseList(MdUnitConverseExportReqVO exportReqVO);

}
