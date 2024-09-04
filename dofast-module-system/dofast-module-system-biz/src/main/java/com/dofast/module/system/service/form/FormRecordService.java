package com.dofast.module.system.service.form;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 表单历史 Service 接口
 *
 * @author 惠智造
 */
public interface FormRecordService {

    /**
     * 创建表单历史
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFormRecord(@Valid FormRecordCreateReqVO createReqVO);

    /**
     * 更新表单历史
     *
     * @param updateReqVO 更新信息
     */
    void updateFormRecord(@Valid FormRecordUpdateReqVO updateReqVO);

    /**
     * 删除表单历史
     *
     * @param id 编号
     */
    void deleteFormRecord(Long id);

    /**
     * 获得表单历史
     *
     * @param id 编号
     * @return 表单历史
     */
    FormRecordDO getFormRecord(Long id);

    /**
     * 获得表单历史列表
     *
     * @param ids 编号
     * @return 表单历史列表
     */
    List<FormRecordDO> getFormRecordList(Collection<Long> ids);

    /**
     * 获得表单历史分页
     *
     * @param pageReqVO 分页查询
     * @return 表单历史分页
     */
    PageResult<FormRecordDO> getFormRecordPage(FormRecordPageReqVO pageReqVO);

    /**
     * 获得表单历史列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 表单历史列表
     */
    List<FormRecordDO> getFormRecordList(FormRecordExportReqVO exportReqVO);

}
