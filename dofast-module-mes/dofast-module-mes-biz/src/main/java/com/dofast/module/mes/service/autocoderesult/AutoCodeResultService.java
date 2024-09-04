package com.dofast.module.mes.service.autocoderesult;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.*;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 编码生成记录 Service 接口
 *
 * @author 芋道源码
 */
public interface AutoCodeResultService {

    List<AutoCodeResultDO> getAutoCodeResultList(AutoCodeResultListVO listVO);
    /**
     * 创建编码生成记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAutoCodeResult(@Valid AutoCodeResultCreateReqVO createReqVO);

    /**
     * 更新编码生成记录
     *
     * @param updateReqVO 更新信息
     */
    void updateAutoCodeResult(@Valid AutoCodeResultUpdateReqVO updateReqVO);

    /**
     * 删除编码生成记录
     *
     * @param id 编号
     */
    void deleteAutoCodeResult(Long id);

    /**
     * 获得编码生成记录
     *
     * @param id 编号
     * @return 编码生成记录
     */
    AutoCodeResultDO getAutoCodeResult(Long id);

    /**
     * 获得编码生成记录列表
     *
     * @param ids 编号
     * @return 编码生成记录列表
     */
    List<AutoCodeResultDO> getAutoCodeResultList(Collection<Long> ids);

    /**
     * 获得编码生成记录分页
     *
     * @param pageReqVO 分页查询
     * @return 编码生成记录分页
     */
    PageResult<AutoCodeResultDO> getAutoCodeResultPage(AutoCodeResultPageReqVO pageReqVO);

    /**
     * 获得编码生成记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 编码生成记录列表
     */
    List<AutoCodeResultDO> getAutoCodeResultList(AutoCodeResultExportReqVO exportReqVO);

}
