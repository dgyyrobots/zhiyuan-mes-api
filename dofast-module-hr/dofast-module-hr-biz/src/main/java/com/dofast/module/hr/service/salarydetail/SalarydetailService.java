package com.dofast.module.hr.service.salarydetail;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.salarydetail.vo.*;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工资明细 Service 接口
 *
 * @author 惠智造
 */
public interface SalarydetailService {

    /**
     * 创建工资明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSalarydetail(@Valid SalarydetailCreateReqVO createReqVO);

    /**
     * 更新工资明细
     *
     * @param updateReqVO 更新信息
     */
    void updateSalarydetail(@Valid SalarydetailUpdateReqVO updateReqVO);

    /**
     * 删除工资明细
     *
     * @param id 编号
     */
    void deleteSalarydetail(Integer id);

    /**
     * 获得工资明细
     *
     * @param id 编号
     * @return 工资明细
     */
    SalarydetailDO getSalarydetail(Integer id);

    /**
     * 获得工资明细列表
     *
     * @param ids 编号
     * @return 工资明细列表
     */
    List<SalarydetailDO> getSalarydetailList(Collection<Integer> ids);

    /**
     * 获得工资明细分页
     *
     * @param pageReqVO 分页查询
     * @return 工资明细分页
     */
    PageResult<SalarydetailDO> getSalarydetailPage(SalarydetailPageReqVO pageReqVO);

    /**
     * 获得工资明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工资明细列表
     */
    List<SalarydetailDO> getSalarydetailList(SalarydetailExportReqVO exportReqVO);

}
