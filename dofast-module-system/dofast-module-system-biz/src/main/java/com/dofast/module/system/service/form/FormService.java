package com.dofast.module.system.service.form;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 系统表单 Service 接口
 *
 * @author 惠智造
 */
public interface FormService {

    /**
     * 创建系统表单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createForm(@Valid FormCreateReqVO createReqVO);

    /**
     * 更新系统表单
     *
     * @param updateReqVO 更新信息
     */
    void updateForm(@Valid FormUpdateReqVO updateReqVO);

    /**
     * 删除系统表单
     *
     * @param id 编号
     */
    void deleteForm(Integer id);

    /**
     * 获得系统表单
     *
     * @param id 编号
     * @return 系统表单
     */
    FormDO getForm(Integer id);

    /**
     * 获得系统表单列表
     *
     * @param ids 编号
     * @return 系统表单列表
     */
    List<FormDO> getFormList(Collection<Integer> ids);

    /**
     * 获得系统表单分页
     *
     * @param pageReqVO 分页查询
     * @return 系统表单分页
     */
    PageResult<FormDO> getFormPage(FormPageReqVO pageReqVO);

    /**
     * 获得系统表单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 系统表单列表
     */
    List<FormDO> getFormList(FormExportReqVO exportReqVO);

}
