package com.dofast.module.qms.service.templateindex;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.templateindex.vo.*;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 检测模板-检测项 Service 接口
 *
 * @author 芋道源码
 */
public interface TemplateIndexService {
    public int deleteByTemplateId(Long templateId);

    /**
     * 创建检测模板-检测项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTemplateIndex(@Valid TemplateIndexCreateReqVO createReqVO);

    /**
     * 更新检测模板-检测项
     *
     * @param updateReqVO 更新信息
     */
    void updateTemplateIndex(@Valid TemplateIndexUpdateReqVO updateReqVO);

    /**
     * 删除检测模板-检测项
     *
     * @param id 编号
     */
    void deleteTemplateIndex(Long id);

    /**
     * 获得检测模板-检测项
     *
     * @param id 编号
     * @return 检测模板-检测项
     */
    TemplateIndexDO getTemplateIndex(Long id);

    /**
     * 获得检测模板-检测项列表
     *
     * @param ids 编号
     * @return 检测模板-检测项列表
     */
    List<TemplateIndexDO> getTemplateIndexList(Collection<Long> ids);

    /**
     * 获得检测模板-检测项分页
     *
     * @param pageReqVO 分页查询
     * @return 检测模板-检测项分页
     */
    PageResult<TemplateIndexDO> getTemplateIndexPage(TemplateIndexPageReqVO pageReqVO);

    /**
     * 获得检测模板-检测项列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 检测模板-检测项列表
     */
    List<TemplateIndexDO> getTemplateIndexList(TemplateIndexExportReqVO exportReqVO);
    List<TemplateIndexDO> getTemplateIndexList(TemplateIndexListVO exportReqVO);

}
