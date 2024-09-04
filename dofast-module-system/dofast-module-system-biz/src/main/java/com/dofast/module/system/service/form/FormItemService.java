package com.dofast.module.system.service.form;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormItemDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 字段 Service 接口
 *
 * @author 惠智造
 */
public interface FormItemService {

    /**
     * 创建字段
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createFormItem(@Valid FormItemCreateReqVO createReqVO);

    /**
     * 更新字段
     *
     * @param updateReqVO 更新信息
     */
    void updateFormItem(@Valid FormItemUpdateReqVO updateReqVO);

    /**
     * 删除字段
     *
     * @param id 编号
     */
    void deleteFormItem(Integer id);

    /**
     * 获得字段
     *
     * @param id 编号
     * @return 字段
     */
    FormItemDO getFormItem(Integer id);

    /**
     * 获得字段列表
     *
     * @param ids 编号
     * @return 字段列表
     */
    List<FormItemDO> getFormItemList(Collection<Integer> ids, Long formId);

    /**
     * 获得字段分页
     *
     * @param pageReqVO 分页查询
     * @return 字段分页
     */
    PageResult<FormItemDO> getFormItemPage(FormItemPageReqVO pageReqVO);

    /**
     * 获得字段列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 字段列表
     */
    List<FormItemDO> getFormItemList(FormItemExportReqVO exportReqVO);

}
