package com.dofast.module.trade.service.calculatemodel;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculatemodel.CalculateModelDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 计价模型 Service 接口
 *
 * @author 惠智造
 */
public interface CalculateModelService {

    /**
     * 创建计价模型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCalculateModel(@Valid CalculateModelCreateReqVO createReqVO);

    /**
     * 更新计价模型
     *
     * @param updateReqVO 更新信息
     */
    void updateCalculateModel(@Valid CalculateModelUpdateReqVO updateReqVO);

    /**
     * 删除计价模型
     *
     * @param id 编号
     */
    void deleteCalculateModel(Integer id);

    /**
     * 获得计价模型
     *
     * @param id 编号
     * @return 计价模型
     */
    CalculateModelDO getCalculateModel(Integer id);

    /**
     * 获得计价模型列表
     *
     * @param ids 编号
     * @return 计价模型列表
     */
    List<CalculateModelDO> getCalculateModelList(Collection<Integer> ids);

    /**
     * 获得计价模型分页
     *
     * @param pageReqVO 分页查询
     * @return 计价模型分页
     */
    PageResult<CalculateModelDO> getCalculateModelPage(CalculateModelPageReqVO pageReqVO);

    /**
     * 获得计价模型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计价模型列表
     */
    List<CalculateModelDO> getCalculateModelList(CalculateModelExportReqVO exportReqVO);

}
